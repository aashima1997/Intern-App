package com.example.aj.View.Fragment

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.example.aj.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class ImageEditor : Fragment() {

lateinit var galleryBtn:Button
lateinit var img:ImageView
lateinit var cameraBtn:Button
lateinit var imgbtn1:ImageButton
lateinit var imgbtn2:ImageButton
lateinit var btn11:Button
lateinit var zoom1:ZoomControls


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_image_editor, container, false)

         galleryBtn = view!!.findViewById(R.id.galleryButton)
         img = view!!.findViewById(R.id.picture)
         cameraBtn = view!!.findViewById(R.id.cameraButton2)
         imgbtn1 = view!!.findViewById(R.id.imageButton1)
         imgbtn2 = view!!.findViewById(R.id.imageButton2)
         btn11 = view!!.findViewById(R.id.save)
         zoom1 = view!!.findViewById(R.id.zoom)

        //checking permissions to read the external storage
        galleryBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (checkSelfPermission(context!!,android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    val perm = arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.CAMERA
                    )
                    requestPermissions(perm, permcode)
                } else {
                    pickImage()
                }

            } else {
                pickImage()
            }
        }

        //checking permissions to access the camera
        cameraBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (ActivityCompat.checkSelfPermission(
                        context!!,
                        android.Manifest.permission.CAMERA
                    ) !=
                    PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        context!! as Activity,
                        arrayOf(android.Manifest.permission.CAMERA),
                        1111
                    )
                } else {
                    pickCamera()
                }
            } else {
                pickCamera()
            }

        }
        //for Zomm controller to visible
        img.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                zoom1.show()
                return false
            }


        })

        //Zooming in
        zoom1.setOnZoomInClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val x: Float = img.scaleX
                val y: Float = img.scaleY
                img.setScaleX(x + 0.2f)
                img.setScaleY(y + 0.2f)
                zoom1.hide()


            }

        })

        //Zooming out
        zoom1.setOnZoomOutClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val x: Float = img.scaleX
                val y: Float = img.scaleY
                if (x == 0.2f && y == 0.2f) {
                    img.setScaleX(x)
                    img.setScaleY(y)
                } else {
                    img.setScaleX(x - 0.2f)
                    img.setScaleY(y - 0.2f)
                    zoom1.hide()
                }
            }
        })
         return view
        }

    private fun pickCamera() {
        //intent action to havea camera
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent1, 1111)    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        //to crop the image
        intent.type = "image/jpg"
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 10);
        intent.putExtra("aspectY", 10);
        intent.putExtra("outputX", 396);
        intent.putExtra("outputY", 396);
        intent.putExtra("noFaceDetection", false);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, imgcode)
    }
//request codes for the image picking
    companion object {
        private val imgcode = 1000   //img picking code
        private val permcode = 1001
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //checking the request codes
        if (requestCode == 1111 && resultCode == Activity.RESULT_OK) {
            //converting to bitmap
            val bmp: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
            img.setImageBitmap(bmp)
            //flipping the image
            imgbtn1.setOnClickListener {
                val mat = Matrix()
                if (mat.preScale(1.0f, 1.0f)) {
                    mat.preScale(-1.0f, 1.0f)
                    val bMapRotate = bmp?.let { it1 ->
                        Bitmap.createBitmap(
                            it1, 0, 0,
                            bmp.getWidth(), bmp.getHeight(), mat, true
                        )
                    }
                    img.setImageBitmap(bMapRotate);
                    btn11.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v: View?) {
                            if (bmp != null) {
                                saveImage(bmp)
                            }
                        }
                    })
                }

                //flip
                imgbtn1.setOnClickListener {
                    if (mat.preScale(-1.0f, 1.0f)) {
                        mat.preScale(1.0f, 1.0f)
                        val bMapRotate = bmp?.let { it1 ->
                            Bitmap.createBitmap(
                                it1, 0, 0,
                                bmp.getWidth(), bmp.getHeight(), mat, true
                            )
                        }
                        val bmd = BitmapDrawable(bMapRotate)
                        img.setImageBitmap(bMapRotate);
                        btn11.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                if (bmp != null) {
                                    saveImage(bmp)
                                }
                            }
                        })

                    }
                   //rotating the image
                    imgbtn2.setOnClickListener {
                        if (mat.preScale(1.0f, 1.0f)) {


                            val mat = Matrix()
                            mat.postRotate(90f);
                            val bMapRotate = bmp?.let { it1 ->
                                Bitmap.createBitmap(
                                    it1, 0, 0,
                                    bmp.getWidth(), bmp.getHeight(), mat, true
                                )
                            }
                            val bmd = BitmapDrawable(bMapRotate)
                            img.setImageBitmap(bMapRotate);
                            btn11.setOnClickListener(object : View.OnClickListener {
                                override fun onClick(v: View?) {
                                    if (bmp != null) {
                                        saveImage(bmp)
                                    }
                                }
                            })
                        }

                         //rotating the image
                        imgbtn2.setOnClickListener {
                            if (mat.postRotate(90f)) {
                                mat.postRotate(180f);
                                val bMapRotate = bmp?.let { it1 ->
                                    Bitmap.createBitmap(
                                        it1, 0, 0,
                                        bmp.getWidth(), bmp.getHeight(), mat, true
                                    )
                                }
                                val bmd = BitmapDrawable(bMapRotate)
                                img.setImageBitmap(bMapRotate);
                                btn11.setOnClickListener(object : View.OnClickListener {
                                    override fun onClick(v: View?) {
                                        if (bmp != null) {
                                            saveImage(bmp)
                                        }
                                    }
                                })

                            }
                            //rotating the image
                            imgbtn2.setOnClickListener {
                                if (mat.postRotate(180f)) {
                                    mat.postRotate(270f);
                                    val bMapRotate = bmp?.let { it1 ->
                                        Bitmap.createBitmap(
                                            it1, 0, 0,
                                            bmp.getWidth(), bmp.getHeight(), mat, true
                                        )
                                    }
                                    val bmd = BitmapDrawable(bMapRotate)
                                    img.setImageBitmap(bMapRotate);
                                    btn11.setOnClickListener(object : View.OnClickListener {
                                        override fun onClick(v: View?) {
                                            if (bmp != null) {
                                                saveImage(bmp)
                                            }

                                        }
                                    })

                                }
                                //rotating the image
                                imgbtn2.setOnClickListener {
                                    if (mat.postRotate(270f)) {
                                        mat.postRotate(0f);
                                        val bMapRotate = bmp?.let { it1 ->
                                            Bitmap.createBitmap(
                                                it1,
                                                0,
                                                0,
                                                bmp.getWidth(),
                                                bmp.getHeight(),
                                                mat,
                                                true
                                            )
                                        }
                                        val bmd = BitmapDrawable(bMapRotate)
                                        img.setImageBitmap(bMapRotate);
                                        btn11.setOnClickListener(object : View.OnClickListener {
                                            override fun onClick(v: View?) {
                                                if (bmp != null) {
                                                    saveImage(bmp)
                                                }
                                            }

                                        })

                                    }


                                }


                            }


                        }
                    }

                }
            }
        }

        if (resultCode == Activity.RESULT_OK && requestCode == imgcode) {
            if (data != null) {

                val bmp: Bitmap? = data?.getParcelableExtra<Bitmap>("data")
                val stream = ByteArrayOutputStream()
                bmp!!.compress(Bitmap.CompressFormat.JPEG, 75, stream)
                img.setImageBitmap(bmp)
                //Flipping the image
                imgbtn1.setOnClickListener {
                    val mat = Matrix()
                    if (mat.preScale(1.0f, 1.0f)) {
                        mat.preScale(-1.0f, 1.0f)
                        val bMapRotate = Bitmap.createBitmap(
                            bmp, 0, 0,
                            bmp.getWidth(), bmp.getHeight(), mat, true
                        )
                        val bmd = BitmapDrawable(bMapRotate)
                        img.setImageBitmap(bMapRotate);
                        btn11.setOnClickListener(object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                saveImage(bmp)
                            }
                        })

                    }
                    imgbtn1.setOnClickListener {
                        if (mat.preScale(-1.0f, 1.0f)) {
                            mat.preScale(1.0f, 1.0f)
                            val bMapRotate = Bitmap.createBitmap(
                                bmp, 0, 0,
                                bmp.getWidth(), bmp.getHeight(), mat, true
                            )
                            val bmd = BitmapDrawable(bMapRotate)
                            img.setImageBitmap(bMapRotate);
                            btn11.setOnClickListener(object : View.OnClickListener {
                                override fun onClick(v: View?) {
                                    saveImage(bmp)
                                }
                            })
                        }

                        //rotating the image
                        imgbtn2.setOnClickListener {
                            val mat = Matrix()
                            mat.postRotate(90f);
                            val bMapRotate = Bitmap.createBitmap(
                                bmp, 0, 0,
                                bmp.getWidth(), bmp.getHeight(), mat, true
                            )
                            val bmd = BitmapDrawable(bMapRotate)
                            img.setImageBitmap(bMapRotate);
                            btn11.setOnClickListener(object : View.OnClickListener {
                                override fun onClick(v: View?) {
                                    saveImage(bmp)
                                }
                            })

                            //rotating the image
                            imgbtn2.setOnClickListener {
                                if (mat.postRotate(90f)) {
                                    mat.postRotate(180f);
                                    val bMapRotate = Bitmap.createBitmap(
                                        bmp, 0, 0,
                                        bmp.getWidth(), bmp.getHeight(), mat, true
                                    )
                                    val bmd = BitmapDrawable(bMapRotate)
                                    img.setImageBitmap(bMapRotate);
                                    btn11.setOnClickListener(object : View.OnClickListener {
                                        override fun onClick(v: View?) {
                                            saveImage(bmp)
                                        }
                                    })

                                }
                                //rotating the image
                                imgbtn2.setOnClickListener {
                                    if (mat.postRotate(180f)) {
                                        mat.postRotate(270f);
                                        val bMapRotate = Bitmap.createBitmap(
                                            bmp, 0, 0,
                                            bmp.getWidth(), bmp.getHeight(), mat, true
                                        )
                                        val bmd = BitmapDrawable(bMapRotate)
                                        img.setImageBitmap(bMapRotate);
                                        btn11.setOnClickListener(object : View.OnClickListener {
                                            override fun onClick(v: View?) {
                                                saveImage(bmp)
                                            }
                                        })

                                    }
                                    //rotating the image
                                    imgbtn2.setOnClickListener {
                                        if (mat.postRotate(270f)) {
                                            mat.postRotate(0f);
                                            val bMapRotate = Bitmap.createBitmap(
                                                bmp,
                                                0,
                                                0,
                                                bmp.getWidth(),
                                                bmp.getHeight(),
                                                mat,
                                                true
                                            )
                                            val bmd = BitmapDrawable(bMapRotate)
                                            img.setImageBitmap(bMapRotate);
                                            btn11.setOnClickListener(object : View.OnClickListener {
                                                override fun onClick(v: View?) {
                                                    saveImage(bmp)
                                                }
                                            })

                                        }


                                    }


                                }

                            }

                        }
                    }
                }
            }
        }
    }

    private fun saveImage(bmp: Bitmap) {
        var imageOutStream: OutputStream
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            //image name
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "image.jpg");
            // image type
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            //storage path
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/" + "Camera");

            val uri = context!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            imageOutStream = uri?.let { context!!.getContentResolver().openOutputStream(it) }!!
        }

        else
        {
            //creating directory and saving
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val image = File(imagesDir, "image.jpg")
            imageOutStream =  FileOutputStream(image);

        }
        //compreesing the image
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, imageOutStream);
        Toast.makeText(context!!,"ImageSaved",Toast.LENGTH_LONG).show()
        imageOutStream.close();

    }
    }










