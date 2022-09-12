package com.mh.mhapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mh.custom_alert.CustomAlert
import com.mh.custom_alert.Theme
import com.mh.custom_alert.Type
import com.mh.mhapp.State
import com.mh.mhapp.data.model.LoginRequest
import com.mh.mhapp.databinding.ActLoginBinding
import com.mh.mhapp.presentation.vm.LoginViewModel

// @AndroidEntryPoint
class ActLogin : AppCompatActivity() {

    private lateinit var binding: ActLoginBinding

    // private val viewModel by viewModel<LoginViewModel>()
    private val viewModel: LoginViewModel by viewModels()

    // private val viewModel: LoginViewModel by viewModels()

    private lateinit var customAlert: CustomAlert

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (checkValidInput()) {
                binding.inputUser.error = null
                binding.inputPass.error = null
                // observeLogin()

                viewModel.login(
                    this,
                    LoginRequest(
                        binding.etUser.text.toString().trim(),
                        binding.etPass.text.toString().trim()
                    )
                )
            }

            /*lifecycleScope.launch {
                val database = DataBase(this@ActLogin).getDB()

                database.daoUser().deleteUser()
                database.daoUser().insertUser(
                    UserEntity(
                        "root",
                        "Manuel",
                        "Hidalgo",
                        "master123"
                    )
                )

                val users = database.daoUser().getUser()
                println(users)

                val name = database.daoUser().getName("root")
                println("Name: $name")

                database.daoUser().updatePassword("root", "123456789")

                val users2 = database.daoUser().getUser()
                println(users2)
            }*/
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, ActRegister::class.java))
        }

        viewModel.stateLogin.observe(this) {
            when (it) {
                is State.Loading -> {
                    customAlert = CustomAlert(this, Theme.SYSTEM)
                    customAlert.setType(Type.PROGRESS)
                    customAlert.setCancelable(false)
                    customAlert.setTitle("Iniciando sesión")
                    customAlert.setMessage("Espero un momento de favor...")
                    customAlert.show()
                }

                is State.Success -> {
                    if (customAlert != null) {
                        customAlert.dismiss()
                    }

                    val intentDashboard = Intent(this, ActDashboard::class.java)
                    intentDashboard.putExtra("user", binding.etUser.text.toString())
                    startActivity(intentDashboard)
                    finish()
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
            /*when (it) {
                is State.Loading -> {
                    println("App: Loading")
                }
                is State.Success -> {
                    println("App: Success")

                    val intentDashboard = Intent(this, ActDashboard::class.java)
                    intentDashboard.putExtra("user", binding.etUser.text.toString())
                    startActivity(intentDashboard)
                    finish()
                }
                is State.Failure -> {
                    println("App: Failure ${it.message}")
                }
            }*/
        }
    }

    private fun observeLogin() {
        /*     viewModel.login(
                 LoginRequest(
                     binding.etUser.text.toString().trim(),
                     binding.etPass.text.toString().trim()
                 )
             ).observe(this) {
                 when (it) {
                     is State.Loading -> {
                         println("App: Loading")
                     }
                     is State.Success -> {
                         println("App: Success $it")
                     }
                     is State.Failure -> {
                         println("App: Failure $it")
                     }
                 }
             }*/
    }

    private fun checkValidInput(): Boolean {
        val user = binding.etUser.text.toString().isEmpty()
        val pass = binding.etPass.text.toString().isEmpty()

        if (user) binding.inputUser.error = "User is empty"
        if (pass) binding.inputPass.error = "Password is empty"

        return !user && !pass
    }
}
