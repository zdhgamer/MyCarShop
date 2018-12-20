<html>

/**
* 新增详细服务商品
* @param session
* @param idJson
* @return
*/
@ResponseBody
@RequestMapping("/api/AddNewShopItemByDetailIds")
public ResultMsg AddNewShopItemByDetailIds(HttpSession session,
@RequestParam("detailId") Integer detailId,
@RequestParam("idJson") String idJson)

<body>
<form action="/api/AddNewShopItemByDetailIds" method="post" enctype="multipart/form-data">
    <table class="form">
        <tr>
            <td class="field">detailId：</td>
            <td><input type="text" id="typeId"  class="text" name="detailId" /></td>
        </tr>
        <tr>
            <td class="field">idJson：</td>
            <td><input type="text" id="id"  class="text" name="idJson" /></td>
        </tr>

        <tr>
            <td></td>
            <td><label class="ui-blue"><input type="submit" name="submit" value="submit_insert" /></label></td>
        </tr>
    </table>
</form>


<form action="/api/UpdateShopItemByDetailIds" method="post" enctype="multipart/form-data">
    <table class="form">
        <tr>
            <td class="field">detailId：</td>
            <td><input type="text" id="detailId"  class="text" name="detailId" /></td>
        </tr>
        <tr>
            <td class="field">idJson：</td>
            <td><input type="text" id="idJson"  class="text" name="idJson" /></td>
        </tr>

        <tr>
            <td></td>
            <td><label class="ui-blue"><input type="submit" name="submit" value="submit_update" /></label></td>
        </tr>
    </table>
</form>

</body>

</html>