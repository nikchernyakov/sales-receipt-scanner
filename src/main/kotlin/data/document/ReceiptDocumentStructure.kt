package data.document

class ReceiptDocument(val shopInfo: ShopInfo,
                      val purchaseInfo: PurchaseInfo)

class PurchaseInfo(val total: Float,
                   val items: MutableList<PurchaseItem>)

class PurchaseItem(val itemName: String,
                   val itemCount: Int,
                   val price: Float)

class ShopInfo(val shopName: String)
