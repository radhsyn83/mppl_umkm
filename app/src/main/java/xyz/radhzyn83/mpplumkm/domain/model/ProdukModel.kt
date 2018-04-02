package xyz.radhzyn83.mpplumkm.domain.model

/**
 * Created by FathurRadhy on 3/31/2018.
 */
data class ProdukModel (
    val status: String,
    val error: String,
    val produk: ArrayList<ProdukModelResult>
)

data class ProdukModelResult (
    val id: String,
    val id_user: String,
    val nama: String,
    val id_produk: String,
    val kode: String,
    val merek: String,
    val berat: String,
    val harga: String,
    val diskon: String,
    val deskripsi: String,
    val shorturl: String,
    val custome: String,
    val kontak: String,
    val text: String,
    val date_add: String,
    val link: String,
    val nama_user: String,
    val profile_foto: String
)