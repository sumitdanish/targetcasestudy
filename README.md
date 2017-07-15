Read ME

execution sequence of the project

1.clound-config-server
2.cloud-monitoring-service
3.seller-service
4.mongodb-cache-as-service
5.user-kart-service
6.elb-as-service
7.customer-search

Post Data For Seller
localhost:8080/product/v1/seller/save
{
	"seller_id":"targ87et21",
	"product_isbn":"87",
	"product_price":123.0,
	"product_quantity":1092,
	"product_name":"Moto365",
	"product_sell_quantity":"0",
 "is_product_active":1
}

Update
localhost:8080/product/v1/seller/update/target1123
{
	"seller_id":"target212",
	"product_price":12213.0,
	"product_quantity":1092,
	"product_name":"Moto365",
	"product_sell_quantity":"0",
 "is_product_active":1
}

http://localhost:8080/product/v1/seller/selleritemlist/target212?type=ACTIVE

http://localhost:8080/product/v1/seller/statement/target212?type=PURCHASED

Consumer Opeation :

localhost:8080/product/v1/search1/search/Moto

Add item to kart
localhost:8080/product/v1/consumer/addItem
{
	    "userId":"sumit123",
      "productQuantity": 50,
      "sellerName": "target123",
      "productId": "target123",
      "productName": "Motot65",
      "productPrice": 96.0,
      "status": "In Stock"
}

Get item from kart
http://localhost:8080/product/v1/consumer/kartItemList/sumit123

Final checkout list (We can put the same response what ever we are getting from request with setting flag for purchase and payment status
)
localhost:8080/product/v1/consumer/checkout

Search item list from checkout history
localhost:8080/product/v1/consumer/checkoutlist/userId

for canceling the checkout proder
localhost:8080/product/v1/consumer/cancel/prodid

View the list of cancel item
localhost:8080/product/v1/consumer/cancelList/userid


all the configuration for DB and MongoDB is written in config_file dir.



