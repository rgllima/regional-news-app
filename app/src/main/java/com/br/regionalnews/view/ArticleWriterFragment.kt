package com.br.regionalnews.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.br.regionalnews.R
import com.br.regionalnews.config.RetrofitInstance
import com.br.regionalnews.config.SaveArticleRequest
import com.br.regionalnews.factories.ArticleAuthorsViewModelFactory
import com.br.regionalnews.factories.ArticleWriterViewModelFactory
import com.br.regionalnews.model.Article
import com.br.regionalnews.repository.ArticleRepository
import com.br.regionalnews.view.articlewebview.ArticleWebviewFragment
import com.br.regionalnews.viewmodel.ArticleAuthorsViewModel
import com.br.regionalnews.viewmodel.ArticleWriterViewModel
import kotlinx.android.synthetic.main.fragment_article_write.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import com.google.android.gms.common.util.IOUtils.toByteArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream


class ArticleWriterFragment : Fragment() {

    lateinit var viewModel: ArticleWriterViewModel

    val article: MutableLiveData<Article> = MutableLiveData()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            holderImageView.visibility = View.VISIBLE
            image_view.setImageURI(data?.data)
            selectImageBtn.visibility = View.GONE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadArguments(arguments)
        initButtons()
        viewModel = createViewModel()

        article.observe(this, androidx.lifecycle.Observer {
            if(article.value != null){
                this.initButtons()
                this.updateValues()

            }
        })

        viewModel.loading.observe(this, androidx.lifecycle.Observer {
            if(it){
                loadingWriteFrag.visibility = View.VISIBLE
            }else {
                loadingWriteFrag.visibility = View.GONE
            }
        })

        viewModel.message.observe(this, androidx.lifecycle.Observer {
            if(!it.isEmpty()){
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun updateValues(){
        articleTitle.setText(article.value?.title)
        articleText.setText(article.value?.description)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.br.regionalnews.R.layout.fragment_article_write, container, false)
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Log.d("==================", "Falha ao ler permissões!")
                }
            }
        }
    }


    fun initButtons(){
        image_view.setOnClickListener {
            //check runtime permission
            if (SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)  ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }

        selectImageBtn.setOnClickListener {
            //check runtime permission
            if (SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)  ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }

        article_publish_back_btn.setOnClickListener {
            findNavController().popBackStack()
        }

        articleArchiveBtn.setOnClickListener {
            viewModel.archiveArticle(article.value!!.title, article.value!!.description, article.value?._id)
        }

        articleDraftBtn.setOnClickListener {
            var title = articleTitle.text.toString()
            var text = articleText.text.toString()
            viewModel.draftArticle(title, text, article.value?._id)
        }

        articleDraftBtn.visibility = if (this.article.value == null) View.VISIBLE else View.GONE
        articleArchiveBtn.visibility = if (article.value?.isArchived == false) View.VISIBLE else View.GONE

        if(this.article.value == null){
            articlePublishBtn.setOnClickListener {

                val bitmap = image_view.drawable.toBitmap()
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                val bytearray = baos.toByteArray()
                val img_str = Base64.getEncoder().encodeToString(bytearray)

                var image = img_str
                var title = articleTitle.text.toString()
                var text = articleText.text.toString()

                viewModel.saveArticle(image, title, text, "")
            }
        }else {
            if(!article.value!!.isArchived) {
                articlePublishBtn.setText("Editar")
            }else {
                articlePublishBtn.setText("Editar e desarquivar")
            }

            articlePublishBtn.setOnClickListener {
                var title = articleTitle.text.toString()
                var text = articleText.text.toString()

                viewModel.editArticle(title, text, article.value?._id)
            }

        }



    }

    private fun createViewModel(): ArticleWriterViewModel {
        val factory = ArticleWriterViewModelFactory(ArticleRepository, activity?.application!!)

        return ViewModelProviders.of(this, factory).get(ArticleWriterViewModel::class.java)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
        private const val ARTICLE_ARGUMENT = "article"

        fun createArguments(article: Article): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(ARTICLE_ARGUMENT, article)

            return bundle
        }

        // Isso deve ficar nessa classe
        fun newInstance(): ArticleWebviewFragment {
            return ArticleWebviewFragment()
        }
    }

    fun loadArguments(arguments: Bundle?) {
        if (arguments == null) {
            return
        }

        val article: Article? = arguments.get(ARTICLE_ARGUMENT) as Article
        this.article.postValue(article)
    }
}