package xyz.radhzyn83.mpplumkm.domain.view

import xyz.radhzyn83.mpplumkm.domain.model.DetailProdukModel

/**
 * Created by FathurRadhy on 3/7/2018.
 */
interface DetailView {
    fun onGetSuccess(res: DetailProdukModel)
    fun onGetError(e: String)
}