<html>

<body>
<form action="/api/AddShopGetCarUserInfo" method="post" enctype="multipart/form-data">
    <table class="form">
        <tr>
            <td class="field">驾照地址：</td>
            <td><input type="file" id="hdpicpath"  class="text" name="drivePic" /></td>
            <td class="field"id="hderrorinfo" style="margin-left:0px">${sessionScope.uploadhdError}</td>
        </tr>
        <tr>
            <td class="field">真实姓名：</td>
            <td><input type="text" id="realName"  class="text" name="realName" /></td>
        </tr>
        <tr>
            <td class="field">手机号码：</td>
            <td><input type="text" id="phoneNumber"  class="text" name="phoneNumber" /></td>
        </tr>
        <tr>
            <td class="field">身份证号码：</td>
            <td><input type="text" id="idCardNumber"  class="text" name="idCardNumber" /></td>
        </tr>
        <tr>
            <td></td>
            <td><label class="ui-blue"><input type="submit" name="submit" value="添加" /></label></td>
        </tr>
    </table>
</form>
</body>

</html>