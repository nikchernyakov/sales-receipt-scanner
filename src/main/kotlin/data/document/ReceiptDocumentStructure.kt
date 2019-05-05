package data.document

class ReceiptDocument(val shopInfo: ShopInfo,
                      val purchaseInfo: PurchaseInfo)

class PurchaseInfo(val total: Float,
                   val items: MutableList<PurchaseItem>)

class PurchaseItem() {

    var itemName: String = ""
    var itemCount: Int = 1
    var price: Float = 0f

    constructor(_itemName: String, _itemCount: Int, _price: Float) : this() {
        itemName =_itemName
        itemCount = _itemCount
        price = _price
    }

    override fun toString(): String {
        return "PurchaseItem(itemName='$itemName', itemCount=$itemCount, price=$price)"
    }
}


class ShopInfo(val shopName: String)
