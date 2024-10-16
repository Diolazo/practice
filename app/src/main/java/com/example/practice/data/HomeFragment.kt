package com.example.practice.data

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.practice.R
import com.example.practice.data.files.Product

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val cite: ImageView = view.findViewById(R.id.btn_cite)

        cite.setOnClickListener {
            val intent = Intent(requireActivity(), ProductListActivity::class.java)
            startActivity(intent)
        }

        
        val cea: ImageView = view.findViewById(R.id.btn_cea)

        cea.setOnClickListener{
            val intent = Intent(requireActivity(), ProductListActivity::class.java)
            startActivity(intent)
        }

        val cma: ImageView = view.findViewById(R.id.btn_cma)

        cma.setOnClickListener{
            val intent = Intent(requireActivity(), ProductListActivity::class.java)
            startActivity(intent)
        }

        val cela: ImageView = view.findViewById(R.id.btn_cela)

        cela.setOnClickListener{
            val intent = Intent(requireActivity(), ProductListActivity::class.java)
            startActivity(intent)
        }

        val cahs: ImageView = view.findViewById(R.id.btn_cahs)

        cahs.setOnClickListener{
            val intent = Intent(requireActivity(), ProductListActivity::class.java)
            startActivity(intent)
        }
        val ccje: ImageView = view.findViewById(R.id.btn_ccjse)

        ccje.setOnClickListener{
            val intent = Intent(requireActivity(), ProductListActivity::class.java)
            startActivity(intent)
        }
        val cas: ImageView = view.findViewById(R.id.btn_cas)

        cas.setOnClickListener{
            val intent = Intent(requireActivity(), ProductListActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
