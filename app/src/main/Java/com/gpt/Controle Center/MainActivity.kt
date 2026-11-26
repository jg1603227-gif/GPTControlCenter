package com.gpt.controlcenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControlCenterTheme {
                ControlCenterScreen()
            }
        }
    }
}

private val Background = Color(0xFF0B1020)
private val Panel = Color(0xFF151E31)
private val Accent = Color(0xFF76A9FA)
private val Muted = Color(0xFF9AA8BD)

@Composable
private fun ControlCenterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Accent,
            background = Background,
            surface = Panel,
            onBackground = Color.White,
            onSurface = Color.White
        ),
        content = content
    )
}

private data class PermissionCategory(
    val title: String,
    val description: String,
    val icon: ImageVector
)

@Composable
private fun ControlCenterScreen() {
    val categories = listOf(
        PermissionCategory("Aplicativos", "Abrir e alternar entre apps", Icons.Default.Apps),
        PermissionCategory("Sistema", "Volume, brilho e ajustes permitidos", Icons.Default.Settings),
        PermissionCategory("Arquivos", "Criar, copiar e organizar arquivos", Icons.Default.Folder),
        PermissionCategory("Automações", "Executar rotinas e macros", Icons.Default.Waves),
        PermissionCategory("Internet", "Abrir páginas e serviços", Icons.Default.Language),
        PermissionCategory("Acesso avançado", "Ações que exigem permissões especiais", Icons.Default.Shield)
    )
    val enabled = remember {
        mutableStateMapOf<String, Boolean>().apply {
            categories.forEach { put(it.title, false) }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("◈", color = Accent, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Column(modifier = Modifier.padding(start = 12.dp)) {
                        Text("GPT CONTROL", fontSize = 12.sp, color = Muted, letterSpacing = 2.sp)
                        Text("Control Center", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(Modifier.height(18.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("●", color = Color(0xFFFBBF24), fontSize = 12.sp)
                            Text("  Ainda não conectado", color = Muted, fontSize = 13.sp)
                        }
                        Spacer(Modifier.height(10.dp))
                        Text("Sua central de autorizações", fontSize = 19.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(5.dp))
                        Text(
                            "Defina quais categorias poderão receber solicitações do GPT. Nenhuma ação é executada nesta versão inicial.",
                            color = Muted,
                            fontSize = 13.sp,
                            lineHeight = 19.sp
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text("PERMISSÕES POR CATEGORIA", color = Muted, fontSize = 11.sp, letterSpacing = 1.5.sp)
            }

            items(categories.size) { index ->
                val category = categories[index]
                Card(
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = category.icon,
                            contentDescription = null,
                            tint = Accent
                        )
                        Column(
                            modifier = Modifier.weight(1f).padding(horizontal = 12.dp)
                        ) {
                            Text(category.title, fontWeight = FontWeight.SemiBold)
                            Spacer(Modifier.height(3.dp))
                            Text(category.description, color = Muted, fontSize = 12.sp)
                        }
                        Switch(
                            checked = enabled[category.title] == true,
                            onCheckedChange = { enabled[category.title] = it }
                        )
                    }
                }
            }

            item {
                Text(
                    "Próxima etapa: persistir as permissões e criar a fila de solicitações com aprovação por categoria.",
                    color = Muted,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
