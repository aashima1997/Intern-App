package com.example.aj.View.Fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.example.aj.R
import com.example.aj.View.Activity.NavDrawer
import com.github.barteksc.pdfviewer.PDFView

class pdfViewer : Fragment() {

    lateinit var pdfBtn: Button
    lateinit var pdfViewer: PDFView


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_pdf_viewer, container, false)
        pdfBtn = view!!.findViewById(R.id.pdfButton)
        pdfViewer = view!!.findViewById(R.id.PDFView)
        val  toolbar1=view!!.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        //toolbar with icon
        toolbar1.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        //navigating to navDrawer Activity
        toolbar1.setNavigationOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = (Intent(context, NavDrawer::class.java))
                startActivity(intent)
            }
        })


        pdfBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (checkSelfPermission(
                        getActivity()!!,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    val perm1 = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(perm1, 101)
                } else {
                    pickPdf()
                }
            } else {
                pickPdf()
            }

        }


    return view
    }

    private fun pickPdf() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type="*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 111) {
            pdfViewer.fromUri(data?.data)
                .defaultPage(0)
                .load()
        }}}






