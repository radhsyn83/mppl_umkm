package xyz.radhzyn83.mpplumkm.domain.view

import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel

/**
 * Created by FathurRadhy on 3/15/2018.
 */
interface ProdukView {
    fun onGetSuccess(res: ProdukModel)
    fun onError(msg: String)
}