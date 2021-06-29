package com.jason.cameraxandfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jason.cameraxandfirebase.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cam.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.send.setOnClickListener{
            val namadepaninput = binding.Firstname.text.toString()
            val namabelakanginput = binding.lastname.text.toString()
            val umurinput = binding.age.text.toString()
            val pekerjaaninput = binding.job.text.toString()
            val alamatinput = binding.address.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Pengguna")
            val User = user(namadepaninput,namabelakanginput,umurinput,pekerjaaninput,alamatinput)
            println(namadepaninput)
            database.child(namadepaninput).setValue(User).addOnSuccessListener {
                binding.Firstname.text.clear()
                binding.lastname.text.clear()
                binding.age.text.clear()
                binding.job.text.clear()
                binding.address.text.clear()

                Toast.makeText(this,"Data Sukses disimpan",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {

                Toast.makeText(this,"Data Gagal disimpan",Toast.LENGTH_SHORT).show()

            }



        }
    }
}