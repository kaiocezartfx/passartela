package com.example.contadorapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Forçamos um esquema de cores claro manualmente
private val EsquemaClaroForcado = lightColorScheme(
    primary = Color(0xFFCDDC39),
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Usamos o MaterialTheme com o esquema claro definido aqui em cima
            MaterialTheme(colorScheme = EsquemaClaroForcado) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White // Fundo sempre branco
                ) {
                    App()
                }
            }
        }
    }
}


@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {

        composable("inicio") {
            TelaInicial(
                irParaContato = {
                    navController.navigate("contato")
                }
            )
        }

        composable("contato") {
            TelaContato(
                voltar = {
                    navController.popBackStack()
                }
            )
        }
    }
}


// =================================
// TELA INICIAL
// =================================

@Composable
fun TelaInicial(
    irParaContato: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Reforçamos o fundo branco
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Meu Aplicativo",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black // Texto sempre preto
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Seja bem-vindo ao nosso aplicativo!",
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = irParaContato,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ir para Contato")
        }
    }
}


// =================================
// TELA DE CONTATO
// =================================

@Composable
fun TelaContato(
    voltar: () -> Unit
) {

    val context = LocalContext.current
    val telefone = "83999999999"
    val email = "exemplo@email.com"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Reforçamos o fundo branco
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Entre em Contato",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "Telefone: (83) 99999-9999", color = Color.Black)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "E-mail: $email", color = Color.Black)

        Spacer(modifier = Modifier.height(25.dp))


        // =========================
        // BOTÃO LIGAR
        // =========================

        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    "tel:$telefone".toUri()
                )
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ligar")
        }


        Spacer(modifier = Modifier.height(15.dp))


        // =========================
        // BOTÃO E-MAIL
        // =========================

        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_SENDTO,
                    "mailto:$email".toUri()
                )
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar e-mail")
        }


        Spacer(modifier = Modifier.height(15.dp))


        // =========================
        // BOTÃO VOLTAR
        // =========================

        Button(
            onClick = { voltar() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
