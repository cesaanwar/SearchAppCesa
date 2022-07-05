package com.cesa.searchappcesa.data.remote

import com.cesa.searchappcesa.data.SearchDataSource
import com.cesa.searchappcesa.search.Product
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSearchDataSource @Inject constructor(
    val service: SearchService
): SearchDataSource {

    override suspend fun getProducts(querySearch: String): List<Product> {
        val query = """
            query (${'$'}params: String!) {
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
        """.trimIndent()
        val params = JSONObject()
        params.put("params", "q=$querySearch&device=android&source=search&rows=10")
        val body = HashMap<String, String>()
        body["query"] = query
        body["variables"] = params.toString()
        val res = service.getProducts(body)
        return res.data.searchProduct.products
    }

    override suspend fun cacheProducts(products: List<Product>) {
    }


}