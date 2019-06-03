app.service("musicService",function ($http) {

    this.findAllMusic=function () {
        return $http.get("../music/findAllMusic");
    };

    this.findOneMusic=function (mid) {
        return $http.get("../music/findOneMusic?mid="+mid);
    };
    
    //调用网易云音乐api搜索歌曲
    this.searchMusic=function (keyword) {
        return $http.get("https://api.mlwei.com/music/api/wy/?key=523077333&id="+keyword+"&type=so&cache=1&nu=10");
    };

    this.findMusicLists=function (userId) {
        return $http.get("../music/findMusicLists&userId="+userId);
    };

    this.addMusic=function (entity) {
        return $http.post("../music/addMusic",entity);
    };
    /**
     * 获取歌词
     * @param mid
     * @returns {*}
     */
    this.getLrc=function (mid) {
        return $http.get("https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=lrc&id="+mid);
    }
    /**
     * 分页查询音乐
     * @param currentPage
     * @param pageSize
     * @returns {*}
     */
    this.findMusicByPage=function (currentPage,pageSize) {
        return $http.get("../music/findMusicByPage?currentPage="+currentPage+"&pageSize="+pageSize);
    }
    /**
     * 查询一首歌的评论
     * @param mid
     * @returns {*}
     */
    this.findOneMusicComment=function (mid) {
        return $http.get("../comment/findOneMusicComment?mid="+mid);
    };

    //查询实体
    this.findOneUser=function(id){
        return $http.get('../user/findOneUser?id='+id);
    };
    /**
     * 添加评论
     * @param entity
     * @returns {*}
     */
    this.addComment=function(entity){
        return $http.post("../comment/addComment",entity);
    };
    /**
     * 通过用户名获取id
     * @returns {*}
     */
    this.getUserIdByName=function () {
        return $http.get("../user/getUserIdByName")
    };
    /**
     * 单个删除
     * @param musicId
     * @returns {*}
     */
    this.deleteSingle=function (musicId) {
        return $http.get("../music/deleteSingle?musicId="+musicId);
    }
});