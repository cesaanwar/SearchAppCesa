package com.cesa.searchappcesa.domain.request

class GetShopsRequest(val query: String, val start: Int): BaseGqlRequest() {

    override fun getQueryString(): String = """
        query (${'$'}params: String!) {
          aceSearchShop(params:${'$'}params) {
            shops {
              shop_id
              shop_image
              shop_name
              shop_location
            }
          }
        }

    """.trimIndent()

    override fun getParams(): String = "q=$query&device=android&source=search&rows=10&start=$start"

}