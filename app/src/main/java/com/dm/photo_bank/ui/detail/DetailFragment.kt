package com.dm.photo_bank.ui.detail

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.dm.photo_bank.R
import com.dm.photo_bank.core.FileUtils
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.coroutines.launch

class DetailFragment: Fragment(R.layout.detail_fragment) {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPermissionCallback()
        initView()
    }

    private fun initView() {
        ivDetail.load(args.photoUrls.regular)
        btSave.setOnClickListener(::saveImageClick)
    }

    private fun setPermissionCallback() {
        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    saveImage()
                }
            }
    }

    private fun saveImageClick(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                saveImage()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                showNeedAlert()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    private fun saveImage() {
        lifecycleScope.launch {
            FileUtils.saveImageToStore(
                FileUtils.getBitmap(args.photoUrls.regular,requireContext()),
                requireContext()
            )
            Toast.makeText(context,"Saved!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showNeedAlert() {
        AlertDialog.Builder(requireContext()).also {
                it.setTitle("Permission")
                it.setMessage("App need permission for save pic to gallery")
                it.setPositiveButton("Ok") { _, _ ->
                    requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.create()
            .show()
    }
}