

function Account_Application(){
    this.openClientModel();
}

Account_Application.prototype = {
    openClientModel:function(){
        $(".client_details_button").click(function(){
            var uuid = $(this).attr("appUuid");
            $("#appClientDetails").modal("show");
            $("#appClientDetailsModalBody").load(contextPath + "/account/application/model/client_details/" + uuid + "?d=" + Math.random(), function () {
                $("#appClientDetailsModalLabel").html("详情");
                Account_Application.prototype._updateValidTime();
            });
        });
    },
    /*更新token 有效期*/
    _updateValidTime:function(){
        $("#appClientDetailsModalSubmit").click(function(){
            var time = $("#validTime").val();
            var clientId = $("#clientId").val();
            //检查时间的有效性，大于等于1
            var reg=/^[-+]?\d*$/;
            if(!reg.test(time)){
                alert("请输入有效的整数！");
                return;
            }
            //请求接口，更新时间
            var options = { validTime:time,
                            clientId:clientId};

            $.ajax({
                url:contextPath+"/account/application/update_token_valid_time",
                method:'post',
                contentType: "application/json",
                data:JSON.stringify(options),
                dataType:"json",
                success:function(data){
                    if(data.success == true){
                        alert("修改成功");
                    }else{
                        alert("操作失败，["+data.errorMessage+"]");
                    }
                },error:function(){

                }
            });

            $("#appClientDetails").modal("hide");
        });

    }
}