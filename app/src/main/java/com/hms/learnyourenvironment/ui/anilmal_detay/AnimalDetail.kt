package com.hms.learnyourenvironment.ui.anilmal_detay


import android.Manifest
import android.app.Dialog
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.hms.learnyourenvironment.R
import com.hms.learnyourenvironment.base.BaseFragment
import com.hms.learnyourenvironment.data.model.AnimalItem
import com.hms.learnyourenvironment.databinding.FragmentAnimalDetailBinding
import com.hms.learnyourenvironment.utils.ASRHelper
import com.hms.learnyourenvironment.utils.TextToSpeechHelper
import com.hms.learnyourenvironment.utils.doAfter
import com.huawei.hms.mlsdk.common.MLApplication
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AnimalDetail : BaseFragment<AnimalDetailViewModel, FragmentAnimalDetailBinding>() {
    @Inject
    lateinit var asrHelper: ASRHelper
    var animal = AnimalItem("", -1, "")
    private val animalDetailFragmentArgs by navArgs<AnimalDetailArgs>()
    private var textToSpeechHelper: TextToSpeechHelper? = null
    private val viewModel: AnimalDetailViewModel by viewModels()
    lateinit var materialDialog: MaterialDialog

    override fun getFragmentViewModel(): AnimalDetailViewModel {
        return viewModel
    }

    override fun getFragmentViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAnimalDetailBinding {
        return FragmentAnimalDetailBinding.inflate(inflater, container, false)
    }

    fun randomNumber(): Int {
        var rnds = (60..100).random()
        return rnds
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MLApplication.initialize(requireContext())
        MLApplication.getInstance().apiKey =
            "DAEDAG0or67nkSEyjDTy5vGQ9sjFW1KYYxnu4olowIk3Ti0flTOzSS0IckOaz4F2onRuPKOnZH08gcfFWttwUZOxcAjiIr1BthgFaw=="

        //bottomSheetTTS()

        animalDetailFragmentArgs?.let {
            animal = it.animalItem!!
            fragmentViewBinding.animalImage.setImageResource(animal.animalImg)
            fragmentViewBinding.animalIdDetail.text = animal.animalName
            fragmentViewBinding.animalIdDetail.typeface = Typeface.DEFAULT_BOLD
            fragmentViewBinding.animalIdDetail.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            fragmentViewBinding.description.text = animal.animalDescription
        }
        initTTS()
        materialSheet()
        fragmentViewBinding.loadingAnimation.setOnClickListener {
            fragmentViewBinding.loadingAnimation.playAnimation()
            fragmentViewBinding.loadingAnimation.loop(true)
            permission()
        }


    }

    override fun setupListeners() {
        super.setupListeners()
        fragmentViewBinding.backButton.setOnClickListener {
            val action = AnimalDetailDirections.actionAnimalDetailToAnimalList()
            view?.findNavController()?.navigate(action)
        }
    }

    //Normal dialog
    private fun bottomSheetResult() {
        val dialog = Dialog(requireContext())
        val view = layoutInflater.inflate(R.layout.result_custom_dialog, null)
        dialog.setContentView(view)
        //view.resultNumber.text = "% " + randomNumber()
        dialog.show()
    }

    private fun resultDialog() {
        materialDialog = MaterialDialog(
            requireContext()
        ).customView(
            view = LayoutInflater.from(context).inflate(
                R.layout.result_custom_dialog,
                fragmentViewBinding.root,
                false
            )
        )
            .cornerRadius(30f)
            .show {
                val tvResult = this.findViewById<TextView>(R.id.resultNumber)
                val ivReturn = this.findViewById<ImageView>(R.id.ivReturn)
                val ivHome = this.findViewById<ImageView>(R.id.ivHome)
                tvResult.text = "% " + randomNumber()
                ivReturn.setOnClickListener {
                    dismiss()
                }
                ivHome.setOnClickListener {
                    dismiss()
                    findNavController().popBackStack()
                }
            }
    }

    fun materialSheet() {
        materialDialog = MaterialDialog(
            requireContext(),
            BottomSheet()
        ).customView(
            view = LayoutInflater.from(context).inflate(
                R.layout.bottom_sheet,
                fragmentViewBinding.root,
                false
            )
        ).setPeekHeight(setMaxHeightPercentage())
            .cornerRadius(30f)
            .show {
                textToSpeechHelper?.startTTS(animal.animalName + " "+ animal.animalDescription)
            }.onDismiss {
                textToSpeechHelper?.stopTTS()
            }
    }


    private fun setMaxHeightPercentage(): Int {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels * 45 / 100
    }

    // Initialize Text to speech
    private fun initTTS() {

        textToSpeechHelper = TextToSpeechHelper { ttsPlayingStatus ->
            when (ttsPlayingStatus) {
                TextToSpeechHelper.TTSPlayingStatus.PLAYING -> {

                }

                TextToSpeechHelper.TTSPlayingStatus.PAUSED -> {

                }

                TextToSpeechHelper.TTSPlayingStatus.NOT_STARTED -> {

                }
                TextToSpeechHelper.TTSPlayingStatus.STOP -> {
                    doAfter(1000) {
                        materialDialog.dismiss()
                    }
                }
            }
        }

    }

    //Destroy text to speech
    override fun onDestroy() {
        textToSpeechHelper?.destroyTTS()
        super.onDestroy()
    }

    // Start listener for ASR
    private fun startListening() {
        asrHelper.listener = onSpeechEventListener
        asrHelper.startRecognizing()
    }

    private val onSpeechEventListener = object : ASRHelper.OnSpeechEventListener {

        override fun onStartListening() {

        }

        override fun onStartCapturing() {}

        override fun onResults(finalResults: String) {
            resultDialog()
            fragmentViewBinding.loadingAnimation.loop(false)
        }

        override fun onError(errorMessage: String) {

        }
    }

    fun permission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                startListening()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }

    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                asrHelper.startRecognizing()
//                startASRWithoutSpeechPickupUI()
            } else {
                Toast.makeText(requireContext(), "Permission Error", Toast.LENGTH_SHORT).show()
            }
        }

}