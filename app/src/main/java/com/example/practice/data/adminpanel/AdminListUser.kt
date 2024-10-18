package com.example.practice.data.adminpanel

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import android.Manifest
import android.content.ContentValues
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import com.example.practice.data.files.DatabaseHelper
import com.example.practice.data.files.Users
import com.example.practice.databinding.DesignAdminListUserBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class AdminListUser : AppCompatActivity() {
    private lateinit var binding: DesignAdminListUserBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var userList: List<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DesignAdminListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        userList = databaseHelper.getAllUsers()
        Log.d("AdminListUser", "User list size: ${userList.size}")
        setupRecyclerView()

        binding.btnAdminBack3.setOnClickListener {
            startActivity(Intent(this, AdminDashboard::class.java))
        }

        binding.btnExport.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                exportToCSV()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewUser.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList) { user ->
            deleteUser(user)
        }
        binding.recyclerViewUser.adapter = userAdapter
    }

    private fun deleteUser(user: Users) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Deletion")
        builder.setMessage("Are you sure you want to delete ${user.name}?")

        builder.setPositiveButton("Yes") { dialog, which ->
            val result = databaseHelper.deleteUser(user.id)
            if (result > 0) {
                Toast.makeText(this, "${user.name} deleted", Toast.LENGTH_SHORT).show()
                refreshUserList()
            } else {
                Toast.makeText(this, "Failed to delete ${user.name}", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun refreshUserList(){
        userList = databaseHelper.getAllUsers()
        userAdapter = UserAdapter(userList){user ->
            deleteUser(user)
        }
        binding.recyclerViewUser.adapter = userAdapter
    }

    private fun exportToCSV() {
        val fileName = "UpangBazaarAccount.csv"
        val filePath: String

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.Downloads.DISPLAY_NAME, fileName)
                put(MediaStore.Downloads.MIME_TYPE, "text/csv")
                put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }

            val uri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

            try {
                uri?.let {
                    val outputStream = contentResolver.openOutputStream(it)
                    val outputStreamWriter = OutputStreamWriter(outputStream)

                    outputStreamWriter.append("ID,Name,Email\n")
                    for (user in userList) {
                        outputStreamWriter.append("${user.id},${user.name},${user.email}\n")
                    }
                    outputStreamWriter.flush()
                    outputStreamWriter.close()

                    Toast.makeText(this, "Exported to Downloads/$fileName", Toast.LENGTH_SHORT).show()
                } ?: run {
                    Toast.makeText(this, "Failed to create file in Downloads", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Failed to export CSV: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(downloadsDir, fileName)

            try {
                val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))

                outputStreamWriter.append("ID,Name,Email\n")
                for (user in userList) {
                    outputStreamWriter.append("${user.id},${user.name},${user.email}\n")
                }
                outputStreamWriter.flush()
                outputStreamWriter.close()

                Toast.makeText(this, "Exported to Downloads/$fileName", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Failed to export CSV: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                exportToCSV()
            } else {
                Toast.makeText(this, "Permission denied to write to storage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
