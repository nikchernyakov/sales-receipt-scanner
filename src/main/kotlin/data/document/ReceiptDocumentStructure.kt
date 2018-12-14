package data.document

class ReceiptDocument(val shopInfo: ShopInfo,
                      val purchaseInfo: PurchaseInfo)

class PurchaseInfo(val total: Int,
                   val items: MutableList<PurchaseItem>)

class PurchaseItem(val itemName: String,
                   val itemCount: Int,
                   val price: Int)

class ShopInfo(val shopName: String)
