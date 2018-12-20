<html>

/**
* 新增banner图片
* @param session
* @param request
* @param pic
* @return
*/
@ResponseBody
@RequestMapping(value="/api/AddNewShopBannerPic",method= RequestMethod.POST)
public ResultMsg AddNewShopBannerPic(HttpSession session,
HttpServletRequest request,
@RequestParam(value="pic",required=false) MultipartFile pic)

/**
* 删除一张banner图片
* @param session
* @param index
* @return
*/
@ResponseBody
@RequestMapping("/api/DeleteShopBannerPicBuyIndex")
public ResultMsg DeleteShopBannerPicBuyIndex(HttpSession session,
@RequestParam(value="index") Integer index)

/**
* 新增detail图片
* @param session
* @param request
* @param pic
* @return
*/
@ResponseBody
@RequestMapping(value="/api/AddNewShopDetailPic",method= RequestMethod.POST)
public ResultMsg AddNewShopDetailPic(HttpSession session,
HttpServletRequest request,
@RequestParam(value="pic",required=false) MultipartFile pic)

/**
* 删除一张detail图片
* @param session
* @param index
* @return
*/
@ResponseBody
@RequestMapping("/api/DeleteShopDetailPicBuyIndex")
public ResultMsg DeleteShopDetailPicBuyIndex(HttpSession session,
@RequestParam(value="index") Integer index)

<body>
<form action="/api/AddNewShopBannerPic" method="post" enctype="multipart/form-data">
    <table class="form">
        <tr>
            <td class="field">pic：</td>
            <td><input type="file" id="typeId"  class="text" name="pic" /></td>
        </tr>

        <tr>
            <td></td>
            <td><label class="ui-blue"><input type="submit" name="submit" value="submit_insert" /></label></td>
        </tr>
    </table>
</form>


<form action="/api/DeleteShopBannerPicBuyIndex" method="post" enctype="multipart/form-data">
    <table class="form">
        <tr>
            <td class="field">index：</td>
            <td><input type="text" id="index"  class="text" name="index" /></td>
        </tr>

        <tr>
            <td></td>
            <td><label class="ui-blue"><input type="submit" name="submit" value="submit_delete" /></label></td>
        </tr>
    </table>
</form>


<form action="/api/AddNewShopDetailPic" method="post" enctype="multipart/form-data">
    <table class="form">
        <tr>
            <td class="field">pic：</td>
            <td><input type="file" id="typeId1"  class="text" name="pic" /></td>
        </tr>

        <tr>
            <td></td>
            <td><label class="ui-blue"><input type="submit" name="submit" value="submit_insert" /></label></td>
        </tr>
    </table>
</form>


<form action="/api/DeleteShopDetailPicBuyIndex" method="post" enctype="multipart/form-data">
    <table class="form">
        <tr>
            <td class="field">index：</td>
            <td><input type="text" id="index22"  class="text" name="index" /></td>
        </tr>

        <tr>
            <td></td>
            <td><label class="ui-blue"><input type="submit" name="submit" value="submit_delete" /></label></td>
        </tr>
    </table>
</form>

</body>

</html>