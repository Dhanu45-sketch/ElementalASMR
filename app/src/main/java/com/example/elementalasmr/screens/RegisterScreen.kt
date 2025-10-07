// screens/LoginScreen.kt & RegisterScreen.kt (REPLACE BOTH)
package com.example.elementalasmr.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

// ==================== REGISTER SCREEN ====================

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegisterScreen(
        onRegisterClick: () -> Unit,
        onBackClick: () -> Unit
    ) {
        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var confirmPasswordVisible by remember { mutableStateOf(false) }
        var agreeToTerms by remember { mutableStateOf(false) }

        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Create Account") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { paddingValues ->
            if (isLandscape) {
                LandscapeRegisterLayout(
                    fullName = fullName,
                    onNameChange = { fullName = it },
                    email = email,
                    onEmailChange = { email = it },
                    password = password,
                    onPasswordChange = { password = it },
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChange = { confirmPassword = it },
                    passwordVisible = passwordVisible,
                    onPasswordVisibilityChange = { passwordVisible = it },
                    confirmPasswordVisible = confirmPasswordVisible,
                    onConfirmPasswordVisibilityChange = { confirmPasswordVisible = it },
                    agreeToTerms = agreeToTerms,
                    onAgreeToTermsChange = { agreeToTerms = it },
                    onRegisterClick = onRegisterClick,
                    onBackClick = onBackClick,
                    modifier = Modifier.padding(paddingValues)
                )
            } else {
                PortraitRegisterLayout(
                    fullName = fullName,
                    onNameChange = { fullName = it },
                    email = email,
                    onEmailChange = { email = it },
                    password = password,
                    onPasswordChange = { password = it },
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChange = { confirmPassword = it },
                    passwordVisible = passwordVisible,
                    onPasswordVisibilityChange = { passwordVisible = it },
                    confirmPasswordVisible = confirmPasswordVisible,
                    onConfirmPasswordVisibilityChange = { confirmPasswordVisible = it },
                    agreeToTerms = agreeToTerms,
                    onAgreeToTermsChange = { agreeToTerms = it },
                    onRegisterClick = onRegisterClick,
                    onBackClick = onBackClick,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }

    @Composable
    fun PortraitRegisterLayout(
        fullName: String,
        onNameChange: (String) -> Unit,
        email: String,
        onEmailChange: (String) -> Unit,
        password: String,
        onPasswordChange: (String) -> Unit,
        confirmPassword: String,
        onConfirmPasswordChange: (String) -> Unit,
        passwordVisible: Boolean,
        onPasswordVisibilityChange: (Boolean) -> Unit,
        confirmPasswordVisible: Boolean,
        onConfirmPasswordVisibilityChange: (Boolean) -> Unit,
        agreeToTerms: Boolean,
        onAgreeToTermsChange: (Boolean) -> Unit,
        onRegisterClick: () -> Unit,
        onBackClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            RegisterForm(
                fullName = fullName,
                onNameChange = onNameChange,
                email = email,
                onEmailChange = onEmailChange,
                password = password,
                onPasswordChange = onPasswordChange,
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = onConfirmPasswordChange,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = onPasswordVisibilityChange,
                confirmPasswordVisible = confirmPasswordVisible,
                onConfirmPasswordVisibilityChange = onConfirmPasswordVisibilityChange,
                agreeToTerms = agreeToTerms,
                onAgreeToTermsChange = onAgreeToTermsChange,
                onRegisterClick = onRegisterClick,
                onBackClick = onBackClick
            )
        }
    }

    @Composable
    fun LandscapeRegisterLayout(
        fullName: String,
        onNameChange: (String) -> Unit,
        email: String,
        onEmailChange: (String) -> Unit,
        password: String,
        onPasswordChange: (String) -> Unit,
        confirmPassword: String,
        onConfirmPasswordChange: (String) -> Unit,
        passwordVisible: Boolean,
        onPasswordVisibilityChange: (Boolean) -> Unit,
        confirmPasswordVisible: Boolean,
        onConfirmPasswordVisibilityChange: (Boolean) -> Unit,
        agreeToTerms: Boolean,
        onAgreeToTermsChange: (Boolean) -> Unit,
        onRegisterClick: () -> Unit,
        onBackClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Left side - Welcome message
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = "App Logo",
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Join Elemental ASMR",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Start your journey to peace and relaxation",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }

            // Right side - Form
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .verticalScroll(rememberScrollState())
            ) {
                RegisterForm(
                    fullName = fullName,
                    onNameChange = onNameChange,
                    email = email,
                    onEmailChange = onEmailChange,
                    password = password,
                    onPasswordChange = onPasswordChange,
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChange = onConfirmPasswordChange,
                    passwordVisible = passwordVisible,
                    onPasswordVisibilityChange = onPasswordVisibilityChange,
                    confirmPasswordVisible = confirmPasswordVisible,
                    onConfirmPasswordVisibilityChange = onConfirmPasswordVisibilityChange,
                    agreeToTerms = agreeToTerms,
                    onAgreeToTermsChange = onAgreeToTermsChange,
                    onRegisterClick = onRegisterClick,
                    onBackClick = onBackClick
                )
            }
        }
    }

    @Composable
    fun RegisterForm(
        fullName: String,
        onNameChange: (String) -> Unit,
        email: String,
        onEmailChange: (String) -> Unit,
        password: String,
        onPasswordChange: (String) -> Unit,
        confirmPassword: String,
        onConfirmPasswordChange: (String) -> Unit,
        passwordVisible: Boolean,
        onPasswordVisibilityChange: (Boolean) -> Unit,
        confirmPasswordVisible: Boolean,
        onConfirmPasswordVisibilityChange: (Boolean) -> Unit,
        agreeToTerms: Boolean,
        onAgreeToTermsChange: (Boolean) -> Unit,
        onRegisterClick: () -> Unit,
        onBackClick: () -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = fullName,
                onValueChange = onNameChange,
                label = { Text("Full Name") },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null)
                },
                trailingIcon = {
                    IconButton(onClick = { onPasswordVisibilityChange(!passwordVisible) }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = { Text("Confirm Password") },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null)
                },
                trailingIcon = {
                    IconButton(onClick = { onConfirmPasswordVisibilityChange(!confirmPasswordVisible) }) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = confirmPassword.isNotEmpty() && password != confirmPassword,
                supportingText = {
                    if (confirmPassword.isNotEmpty() && password != confirmPassword) {
                        Text("Passwords do not match")
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = agreeToTerms,
                    onCheckedChange = onAgreeToTermsChange
                )
                Text(
                    text = "I agree to the Terms and Conditions",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = fullName.isNotEmpty() &&
                        email.isNotEmpty() &&
                        password.isNotEmpty() &&
                        password == confirmPassword &&
                        agreeToTerms
            ) {
                Text("Create Account", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    style = MaterialTheme.typography.bodyMedium
                )
                TextButton(onClick = onBackClick) {
                    Text("Login")
                }
            }
        }
    }