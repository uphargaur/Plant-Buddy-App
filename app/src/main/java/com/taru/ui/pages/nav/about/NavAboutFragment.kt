package com.taru.ui.pages.nav.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.taru.databinding.NavAboutFragmentBinding
import com.taru.ui.base.FragmentBase
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Niraj on 08-01-2023.
 */
@AndroidEntryPoint
class NavAboutFragment: FragmentBase(false) {

    private val mViewModel: NavAboutViewModel by viewModels()
    private lateinit var vBinding: NavAboutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding =
            NavAboutFragmentBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.lifecycleOwner = this.viewLifecycleOwner

        vBinding.buttonGithub.setOnClickListener() {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/uphargaur/Plant-Buddy-App"))
            startActivity(intent)
        }
        vBinding.buttonJunkiesLabs.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/uphargaur/Plant-Buddy-App"))
            startActivity(intent)
        }

        vBinding.buttonPrivacy.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/uphargaur/Plant-Buddy-App"))
            startActivity(intent)
        }
        vBinding.submitRatingbtn.setOnClickListener()
        {
            val ratingValue = vBinding.ratingbar.rating
           val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("rating")

            val newEntryReference = databaseReference.push()
            newEntryReference.setValue(ratingValue)


        }
    }
}