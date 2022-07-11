package com.cesa.searchappcesa.domain.request

class GetProductsRequest(private val query: String, private val start: Int) : BaseGqlRequest() {

    override fun getQueryString(): String =
        """query(${'$'}params: String!) {
              searchProduct(params:${'$'}params) {
                products {
                  id
                  name
                  price
                  department_id
                  rating_average
                  image_url
                }
              }
            }
        """

    override fun getParams(): String = "q=$query&start=$start&device=android&source=search&rows=10"


}