package com.ammyt.madridshops

import com.ammyt.madridshops.repository.model.ShopEntity
import com.ammyt.madridshops.repository.network.json.JsonEntitiesParser
import org.junit.Test

import com.ammyt.madridshops.util.ReadJsonFile
import org.junit.Assert.*

class JSONParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json__it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shop.json")
        assertTrue(false == shopsJson.isEmpty())

        // parsing
        val parser = JsonEntitiesParser()
        val shops = parser.parse<ShopEntity>(shopsJson)

        assertNotNull(shops)
        assertEquals("Cortefiel - Preciados", shops.name)
    }
}