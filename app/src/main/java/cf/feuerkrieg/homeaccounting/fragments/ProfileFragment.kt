package cf.feuerkrieg.homeaccounting.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.databinding.FragmentProfileLayoutBinding
import java.io.File

private val REQUEST_PHOTO_CAPTURE = 1
private val REQUEST_PHOTO_PICK = 2
private val REQUEST_READ_STORAGE = 3

class ProfileFragment: Fragment() {


    private lateinit var binding: FragmentProfileLayoutBinding
    private lateinit var editIcon: AnimatedVectorDrawableCompat
    private lateinit var checkIcon: AnimatedVectorDrawableCompat

    val isEdit = MutableLiveData<Boolean>(false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentProfileLayoutBinding
            .inflate(inflater, container, false)


        binding.lifecycleOwner = viewLifecycleOwner
        binding.ui = this

        binding.btnCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_PHOTO_CAPTURE)
        }

        binding.btnBrowse.setOnClickListener {

            if(ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImage()
            }
            else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_STORAGE)
            }
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setHasOptionsMenu(true)

        editIcon = AnimatedVectorDrawableCompat.create(
            requireContext(),
            R.drawable.check_to_edit
        )!!

        checkIcon = AnimatedVectorDrawableCompat.create(
            requireContext(),
            R.drawable.edit_to_check
        )!!

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.menu_profile_edit -> {
                editItemSelected(item)
            }

            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }


    private fun editItemSelected(item: MenuItem) {

        isEdit.value = !(isEdit.value!!)

        if(isEdit.value == true) {
            item.icon = checkIcon
            checkIcon.start()
        }
        else {
            item.icon = editIcon
            editIcon.start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode != RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }

        when(requestCode) {

            REQUEST_PHOTO_CAPTURE -> {
                val bitmap = data!!.extras!!.get("data") as Bitmap
                binding.circleImageView.setImageBitmap(bitmap)
            }
            REQUEST_PHOTO_PICK -> {

                val uri = data?.data

                if(uri != null) {

                    val file = uriToFile(uri)

                    if(file != null) {
                        val bitmap = BitmapFactory.decodeStream(
                            requireActivity().contentResolver.openInputStream(uri)
                        )
                        binding.circleImageView.setImageBitmap(bitmap)
                    }
                }
            }

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            REQUEST_READ_STORAGE -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage()
                }
            }
        }
    }


    fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_PHOTO_PICK)
    }

    fun uriToFile(uri: Uri): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

        val cursor = requireActivity().contentResolver.query(uri, filePathColumn,
                                                            null, null, null)

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                val colIndex = cursor.getColumnIndex(filePathColumn[0])
                val path = cursor.getString(colIndex)
                cursor.close()
                return File(path)
            }
            cursor.close()
        }

        return null
    }
}