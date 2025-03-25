package com.example.homeworktbc.presentation.screen.imageFragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.homeworktbc.databinding.FragmentImageBinding
import com.example.homeworktbc.presentation.base.BaseFragment
import com.example.homeworktbc.presentation.screen.BottomSheetFragment
import com.example.homeworktbc.presentation.screen.imageFragment.event.ImageEvent
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class ImageFragment : BaseFragment<FragmentImageBinding>(FragmentImageBinding::inflate) {

    private val imageViewModel: ImageViewModel by activityViewModels()

    override fun start() {
        addImageButtonClickListener()
        observeImageState()
    }

    private fun addImageButtonClickListener() {
        binding.btnAddImage.setOnClickListener {
            BottomSheetFragment { isCamera ->
                if (isCamera) openCamera() else openGallery()
            }.show(parentFragmentManager, "BottomSheetDialog")
        }
    }

    private fun openCamera() {
        cameraLauncher.launch()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            val compressedBitmap = compressBitmap(bitmap)
            if (compressedBitmap != null) {
                imageViewModel.obtainEvent(ImageEvent.SaveImage(compressedBitmap))
            }
        }
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                val compressedBitmap = compressImage(uri)
                imageViewModel.obtainEvent(ImageEvent.SaveImage(compressedBitmap))
            }
        }
    }

    private fun compressBitmap(bitmap: Bitmap?): Bitmap? {
        if (bitmap == null) return null
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        val byteArray = outputStream.toByteArray()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    private fun compressImage(uri: Uri): Bitmap {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val originalBitmap = BitmapFactory.decodeStream(inputStream)
        return Bitmap.createScaledBitmap(originalBitmap, originalBitmap.width * 80 / 100, originalBitmap.height * 80 / 100, false)
    }

    private fun observeImageState() {
        lifecycleScope.launch {
            imageViewModel.viewState.collect { state ->
                state.image?.let { image ->
                    binding.ivImageHolder.setImageBitmap(image)
                }
            }
        }
    }
}
