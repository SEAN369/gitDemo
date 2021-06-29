
//定义获得用户的方法
const getUser = function(){
    //使用axios

    return axios.get("/user?action=checkUserLogin")
}
//导出配置
export {getUser}
/*



export default{
    getUser:function(){
        axios.get("/user?action=checkUserLogin").then(resp=>{
            return resp.data;
           /!* if(resp.data != "1"){
                config.user= resp.data;
                console.log("login.js:" + config.user);
                console.log(config.user);
            }*!/
        })
    }
}*/
