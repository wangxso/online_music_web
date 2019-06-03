app.controller('musicController',function ($scope,$controller,$sce,musicService) {

    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    };
    //查找曲库中所有歌曲
    $scope.findAllMusic=function () {
        musicService.findAllMusic().success(function (response) {
            $scope.list=response;
            //加入候选列表
            for(var i=0;i<$scope.list.length;i++){
                var music=$scope.list[i];
                var poster_url="https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=pic&id=";
                poster_url+=music.musicId;
                data={
                    title:music.name,
                    artist:music.author,
                    mp3:music.src,
                    poster:poster_url
                };
                latest[$scope.list[i].musicId]=data;
            }
        });
    };

    /**
     * 查找单曲
     * @param mid
     */
    $scope.findOneMusic=function (mid) {
        musicService.findOneMusic(mid).success(function (response) {
            $scope.entity=response;
            //信任此连接
            $scope.msrc=$sce.trustAsResourceUrl($scope.entity.src)
        })
    };
    /**
     * 搜索歌曲
     */
    $scope.searchMusic=function () {
        musicService.searchMusic($scope.keyword).success(function (response) {
            $scope.findMusicList=response;
            $scope.list=[];
            for(var i=0;i<$scope.findMusicList.Body.length;i++){
                data={
                    musicId:$scope.findMusicList.Body[i].id,
                    name:$scope.findMusicList.Body[i].title,
                    author:$scope.findMusicList.Body[i].author,
                    publisher:$scope.findMusicList.Body[i].author,
                    src:$scope.findMusicList.Body[i].url,
                    lyricSrc:$scope.findMusicList.Body[i].lrc,
                };
                musicService.addMusic(data);
                console.log(data);
                $scope.list.push(data);
            }

        })
    };

    /**
     * 获取歌词数据
     * @param mid
     */
    $scope.getLrc=function (mid) {
        musicService.getLrc(mid).success(function (response) {
            $scope.lrc=response;
        })
    };
    /**
     * 添加到当前播放列表
     * @param index
     */
    $scope.addToList=function (musicId) {
        musicService.findOneMusic(musicId).success(function (response) {
            myPlaylist.add({
                title:response.name,

                artist:response.author,
                //free:true,
                mp3:response.src,
                //oga:"",
                poster:"https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=pic&id="+response.musicId
            });
            myPlaylist.play(-1);

        })

    };
    // /**
    //  * 分页中添加到列表
    //  * @param index
    //  */
    // $scope.addToListForPage=function (index) {
    //     var k = index+$scope.paginationConf.currentPage*$scope.paginationConf.itemsPerPage;
    //     alert(k);
    //     myPlaylist.add({
    //         title:latest[k].title,
    //
    //         artist:latest[k].artist,
    //         //free:true,
    //         mp3:latest[k].mp3,
    //         //oga:"",
    //         poster:latest[k].poster
    //     });
    //     myPlaylist.play(-1);
    // }

    $scope.getToDetailPage=function () {

    }
    
    $scope.searchMusicById=function () {
        var name = "id";
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            var id = unescape(r[2]);
            $scope.musicId=id;
            $scope.findOneMusic(id);
            $scope.findOneMusicComment(id);

        }
    };
    $scope.nickNameById={}
    $scope.findOneMusicComment=function(mid){
        musicService.findOneMusicComment(mid).success(function (response) {
            $scope.entity.commentList=response;
            if($scope.entity.commentList!=null && $scope.entity.commentList.length>0){
                for(var i=0;i<$scope.entity.commentList.length;i++){

                    var userId = $scope.entity.commentList[i].userId;

                    $scope.entity.commentList[i].comment=$sce.trustAsHtml($scope.entity.commentList[i].comment);

                    musicService.findOneUser($scope.entity.commentList[i].userId).success(function (response) {

                        var name=response.nickName;

                        console.log(userId);
                        if($scope.nickNameById[userId]==null){

                            $scope.nickNameById[userId]=name;

                        }

                    })

                }
                console.log($scope.nickNameById)
            }

        })
    }
    
    $scope.findMusicByPage=function (pages,rows) {
        musicService.findMusicByPage(pages,rows)
            .success(function (response) {
                $scope.paginationConf.totalItems=response.totalNum;
                $scope.list=response.items;
                latest=[]
                //加入候选列表
                for(var i=0;i<$scope.list.length;i++){
                    var music=$scope.list[i];
                    var poster_url="https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=pic&id=";
                    poster_url+=music.musicId;
                    data={
                        title:music.name,
                        artist:music.author,
                        mp3:music.src,
                        poster:poster_url
                    };
                    latest[$scope.list[i].musicId]=data;
                }
        })
    };

    //重新加载列表 数据
    $scope.reloadList=function(){
        //切换页码
        $scope.findMusicByPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };


    $scope.comment={"comment":"","userId":"","musicId":""};
    /**
     * 添加评论
     * @param mid
     */
    $scope.addComment=function (mid) {
        if(content==null || content.length<=0 || content==""){
            alert("内容不能为空");
            return;
        }
        $scope.comment.comment=content;

        $scope.comment.musicId=mid;
        //获取用户id
        musicService.getUserIdByName().success(function (response) {
            $scope.comment.userId=response;
        });
        musicService.addComment($scope.comment).success(function (response) {
            alert(response.message);
            if(response.status==500){
                //获取url中的id参数
                var name = "id";
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) {
                    var id = unescape(r[2]);
                }
                window.location.href="tologin?refer="+id;
            }
        });

    }

    //跳转到refer页面
    $scope.redirect=function (id) {
        window.location.href="musicDetail?id="+id;
    };

    $scope.deleteSingle=function(musicId){
        var r=confirm("您确定要删除?")
        if(r==true){
            musicService.deleteSingle(musicId).success(function (response) {
                alert(response.message);
            })
        }else{
            return;
        }

    }
});