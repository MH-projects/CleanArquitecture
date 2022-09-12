package com.mh.mhapp.presentation.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mh.custom_alert.CustomAlert
import com.mh.custom_alert.Theme
import com.mh.custom_alert.Type
import com.mh.mhapp.R
import com.mh.mhapp.State
import com.mh.mhapp.data.model.RegisterRequest
import com.mh.mhapp.databinding.ActRegisterBinding
import com.mh.mhapp.presentation.DatePickerAlert
import com.mh.mhapp.presentation.vm.RegisterViewModel
import com.mh.mhapp.utils.Field
import com.mh.mhapp.utils.TypeValid
import com.mh.mhapp.utils.Utils

class ActRegister : AppCompatActivity(), View {

    private lateinit var binding: ActRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    private lateinit var customAlert: CustomAlert

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etGender.setOption(R.array.array_gender)
        binding.etState.setOption(R.array.array_state_mx)

        binding.etBirthday.setOnClickListener {
            DatePickerAlert(this, this).show()
        }

        binding.btnRegister.setOnClickListener {
            val inputsValid = Utils.validInputs(
                Field(type = TypeValid.EMPTY, binding.inputName, binding.etName),
                Field(type = TypeValid.EMPTY, binding.inputApaterno, binding.etApaterno),
                Field(type = TypeValid.EMPTY, binding.inputAmaterno, binding.etAmaterno),
                Field(
                    type = TypeValid.DISTINCT_TEXT,
                    binding.inputBirthday,
                    binding.etBirthday,
                    expected = "dd/mm/yyyy"
                ),
                Field(type = TypeValid.EMPTY, binding.inputGender, auto = binding.etGender),
                Field(type = TypeValid.EMPTY, binding.inputEmail, binding.etEmail),
                Field(type = TypeValid.EMPTY, binding.inputState, auto = binding.etState),
                Field(type = TypeValid.EMPTY, binding.inputNumber, binding.etNumber),
                Field(type = TypeValid.EMPTY, binding.inputUser, binding.etUser),
                Field(type = TypeValid.EMPTY, binding.inputPass, binding.etPass),
                Field(type = TypeValid.EMPTY, binding.inputVerifyPass, binding.etVerifyPass)
            )

            if (inputsValid) {
                if (binding.etVerifyPass.text.toString() == binding.etPass.text.toString()) {
                    binding.inputVerifyPass.error = null

                    viewModel.register(
                        RegisterRequest(
                            binding.etUser.text.toString(),
                            binding.etName.text.toString(),
                            binding.etApaterno.text.toString(),
                            binding.etAmaterno.text.toString(),
                            binding.etBirthday.text.toString(),
                            binding.etEmail.text.toString(),
                            binding.etGender.text.toString(),
                            binding.etState.text.toString(),
                            binding.etNumber.text.toString(),
                            binding.etPass.text.toString()
                        )
                    )
                } else {
                    binding.inputVerifyPass.error = "La contraseña debe de coincidir"
                }
            }
        }

        viewModel.stateRegister.observe(this) {
            when (it) {
                is State.Loading -> {
                    customAlert = CustomAlert(this, Theme.SYSTEM)
                    customAlert.setType(Type.PROGRESS)
                    customAlert.setCancelable(false)
                    customAlert.setTitle("Registrando usuario")
                    customAlert.setMessage("Espero un momento de favor...")
                    customAlert.show()
                }

                is State.Success -> {
                    if (customAlert != null) {
                        customAlert.dismiss()
                    }

                    customAlert = CustomAlert(this, Theme.SYSTEM)
                    customAlert.setType(Type.SUCCESS)
                    customAlert.setCancelable(false)
                    customAlert.setTitle("Usuario registrado")
                    customAlert.setPositiveText("Continuar") {
                        customAlert.dismiss()
                        finish()
                    }
                    customAlert.show()
                }

                is State.Failure -> {
                    if (customAlert != null) {
                        customAlert.dismiss()
                    }

                    customAlert = CustomAlert(this, Theme.SYSTEM)
                    customAlert.setType(Type.FAIL)
                    customAlert.setCancelable(false)
                    customAlert.setTitle("Ocurrion un error")
                    customAlert.setMessage(it.message)
                    customAlert.setPositiveText("Aceptar") {
                        customAlert.dismiss()
                    }
                    customAlert.show()
                }
            }
        }
    }

    private fun AutoCompleteTextView.setOption(array: Int) {
        setAdapter(
            ArrayAdapter(
                this@ActRegister,
                R.layout.item_option,
                resources.getStringArray(array)
            )
        )
    }

    override fun showBirthday(birthday: String) {
        binding.etBirthday.setText(birthday)
    }
}

interface View {
    fun showBirthday(birthday: String)
}
