package com.example.myapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.ui.dashboard.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var username: EditText
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var phoneNo: EditText
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup)

        signUpButton = findViewById(R.id.signupButton)
        auth = FirebaseAuth.getInstance()
        firstName = findViewById(R.id.first_name)
        lastName = findViewById(R.id.last_name)
        email = findViewById(R.id.email)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        phoneNo = findViewById(R.id.phone_no)


        signUpButton.setOnClickListener {
            print("button clicked")
            // Retrieve input values
            val firstNameInput = firstName.text.toString().trim()
            val lastNameInput = lastName.text.toString().trim()
            val emailInput = email.text.toString().trim()
            val phoneNoInput = phoneNo.text.toString().trim()
            val usernameInput = username.text.toString().trim()
            val passwordInput = password.text.toString().trim()

            // Validate the inputs
          //  if (validateInput(firstNameInput, lastNameInput, emailInput, phoneNoInput, usernameInput, passwordInput)) {
                // If valid, proceed to sign up the user
                auth.createUserWithEmailAndPassword(emailInput, passwordInput)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Successfully created the user
                            val user = auth.currentUser
                            val uid = user?.uid ?: ""

                            // Save additional details like first name, last name, username,and email in Firestore
                            val userData = hashMapOf(
                                "firstName" to firstNameInput,
                                "lastName" to lastNameInput,
                                "phoneNo" to phoneNoInput,
                                "username" to usernameInput,
                                "email" to emailInput,
                            )

                            // Use Firestore to store the user details securely
                            val db = FirebaseFirestore.getInstance()
                            db.collection("users").document(uid)
                                .set(userData)
                                .addOnSuccessListener {
                                    // Redirect to Dashboard after successfully saving user data
                                    val intent = Intent(this, DashboardActivity::class.java)
                                    startActivity(intent)
                                    finish()  // Close the SignUp Activity
                                }
                                .addOnFailureListener { exception ->
                                    Toast.makeText(this, "Error saving user data: ${exception.message}", Toast.LENGTH_SHORT).show()
                                }

                            Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign-up fails, show an error message
                            Toast.makeText(this, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
      //  }
    }

    private fun validateInput(firstname: String, lastname: String, email: String, phoneNo: String, username: String, password: String): Boolean {
        return when {
            // Validate first name - Only alphabetic characters and non-empty
            firstname.isEmpty() -> {
                firstName.error = "Please enter your first name"
                false
            }
            !firstname.matches(Regex("^[A-Za-z]+$")) -> {
                firstName.error = "First name must only contain alphabetic characters"
                false
            }
            firstname.length < 2 -> {
                firstName.error = "First name must be at least 2 characters"
                false
            }

            // Validate last name - Only alphabetic characters and non-empty
            lastname.isEmpty() -> {
                lastName.error = "Please enter your last name"
                false
            }
            !lastname.matches(Regex("^[A-Za-z]+$")) -> {
                lastName.error = "Last name must only contain alphabetic characters"
                false
            }
            lastname.length < 2 -> {
                lastName.error = "Last name must be at least 2 characters"
                false
            }

            // Validate email
            email.isEmpty() -> {
                this.email.error = "Please enter your email"
                false
            }
            !isValidEmail(email) -> {
                this.email.error = "Invalid email format"
                false
            }

            // Validate phone number
            phoneNo.isEmpty() -> {
                this.phoneNo.error = "Please enter your phone number"
                false
            }
            !isValidPhoneNumber(phoneNo) -> {
                this.phoneNo.error = "Phone number must start with +962 (11 digits) or 079 (10 digits)"
                false
            }

            // Validate username - length between 3 and 15 characters
            username.isEmpty() -> {
                this.username.error = "Please enter a username"
                false
            }
            username.length !in 3..15 -> {
                this.username.error = "Username must be between 3 and 15 characters"
                false
            }

            // Validate password - minimum 6 characters
            password.isEmpty() -> {
                this.password.error = "Please enter a valid password"
                false
            }
            password.length < 6 -> {
                this.password.error = "Password must be at least 6 characters"
                false
            }

            else -> true
        }
    }

    // Email Validation - Strong regex pattern
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        return email.matches(Regex(emailPattern))
    }

    // Phone number validation (Jordan formats)
    private fun isValidPhoneNumber(phoneNo: String): Boolean {
        return phoneNo.matches(Regex("^\\+962\\d{8}\$")) || phoneNo.matches(Regex("^079\\d{7}\$"))
    }
}