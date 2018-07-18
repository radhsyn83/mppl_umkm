package xyz.radhzyn83.mpplumkm.domain.view

import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel

/**
 * Created by FathurRadhy on 3/7/2018.
 */
interface SearchView {
    fun onGetSuccess(res: ProdukModel)
    fun onGetError(e: String)
}