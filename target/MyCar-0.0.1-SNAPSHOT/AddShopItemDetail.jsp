<html>

@ResponseBody
@RequestMapping(value="/api/AddNewShopItemInfo",method= RequestMethod.POST)
public ResultMsg AddNewShopItemInfo(HttpSession session,
HttpServletRequest request,
@RequestParam("typeId") Integer typeId,
@RequestParam(value="pic",required=false) MultipartFile pic,
@RequestParam("useDes") String useDes,
@RequestParam("price") String price,
@RequestParam("carList") String carList


{
"allCarList":[
{
"carId":111,
"subCarIdList":[
{
"carSubId":157
},
{
"carSubId":265
},
{
"carSubId":123
},
{
"carSubId":457
},
]
},
{
"carId":151,
"subCarIdList":[
{
"carSubId":1576
},
{
"carSubId":2655
},
{
"carSubId":1232
},
{
"carSubId":4571
},
]
},
]
}

<body>
<form action="/api/AddNewShopItemInfo" method="post" enctype="multipart/form-data">
    <table class="form">
        <tr>
            <td class="field">图片：</td>
            <td><input type="file" id="hdpicpath"  class="text" name="pic" /></td>
            <td class="field" id="hderrorinfo" style="margin-left:0px">${sessionScope.uploadhdError}</td>
        </tr>
        <tr>
            <td class="field">商品类型id：</td>
            <td><input type="text" id="typeId"  class="text" name="typeId" /></td>
        </tr>
        <tr>
            <td class="field">使用描述：</td>
            <td><input type="text" id="useDes"  class="text" name="useDes" /></td>
        </tr>
        <tr>
            <td class="field">价格：</td>
            <td><input type="text" id="price"  class="text" name="price" /></td>
        </tr>
        <tr>
            <td class="field">选中车辆类型：</td>
            <td><input type="text" id="carList"  class="text" name="carList" /></td>
        </tr>
        <tr>
            <td></td>
            <td><label class="ui-blue"><input type="submit" name="submit" value="添加" /></label></td>
        </tr>
    </table>
</form>
</body>

</html>