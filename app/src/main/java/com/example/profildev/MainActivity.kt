package com.example.profildev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildev.ui.theme.ProfilDevTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDevTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EcranProfil(
                        nom = "Jeevons Eya-Jr Iq",
                        role = "Chef de projet IT",
                        description = "Passionné de technologie et d'astronomie. Je travaille comme chef de projet IT chez Laval Europe.",
                        competences = listOf("Gestion de projet", "Kotlin", "Git", "CI/CD", "Docker"),
                        contact = "linkedin.com/in/jeevons-e-3660a7297",
                        disponibilite = "Ouvert aux échanges sur de nouveaux projets",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Ecran principal : il place la fiche dans l'ecran
@Composable
fun EcranProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    contact: String,
    disponibilite: String,
    modifier: Modifier = Modifier
) {
    // padding externe : c'est l'ecran (le parent) qui decide de l'espace autour de la fiche
    FicheProfil(
        nom = nom,
        role = role,
        description = description,
        competences = competences,
        contact = contact,
        disponibilite = disponibilite,
        onContacterClick = {
            // pour l'instant on ne fait rien, le bouton signale juste le clic
        },
        onDisponibiliteClick = {
            // pareil, pas de logique ici pour le devoir
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

// La fiche complete avec toutes les infos
@Composable
fun FicheProfil(
    nom: String,
    role: String,
    description: String,
    competences: List<String>,
    contact: String,
    disponibilite: String,
    onContacterClick: () -> Unit,
    onDisponibiliteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        // padding interne : espace entre le bord de la fiche et son contenu
        Column(modifier = Modifier.padding(20.dp)) {
            EnTeteProfil(nom = nom, role = role)

            Text(
                text = description,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
            )

            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleMedium
            )
            for (competence in competences) {
                Competence(
                    nom = competence,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            InfoSecondaire(
                titre = "Contact",
                valeur = contact,
                modifier = Modifier.padding(top = 16.dp)
            )

            ZoneDisponibilite(
                texte = disponibilite,
                onClick = onDisponibiliteClick,
                modifier = Modifier.padding(top = 12.dp)
            )

            BoutonAction(
                texte = "Me contacter",
                onClick = onContacterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}

// En-tete : nom + role
@Composable
fun EnTeteProfil(nom: String, role: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = nom,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

// Une competence affichee comme une petite etiquette
@Composable
fun Competence(nom: String, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
    ) {
        Text(
            text = nom,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

// Une info du type "Titre : valeur"
@Composable
fun InfoSecondaire(titre: String, valeur: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = titre, style = MaterialTheme.typography.labelLarge)
        Text(text = valeur)
    }
}

// Zone cliquable perso, donc il faut dire au lecteur d'ecran que c'est un bouton
@Composable
fun ZoneDisponibilite(texte: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = Color(0xFFC8E6C9),
        modifier = modifier
            .fillMaxWidth()
            .clickable(role = Role.Button, onClick = onClick)
            .semantics { contentDescription = "Voir ma disponibilité : $texte" }
    ) {
        Text(
            text = texte,
            modifier = Modifier.padding(12.dp)
        )
    }
}

// Bouton d'action reutilisable
@Composable
fun BoutonAction(texte: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = texte)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilChefDeProjetPreview() {
    ProfilDevTheme {
        EcranProfil(
            nom = "Jeevons Eya-Jr Iq",
            role = "Chef de projet IT",
            description = "Passionné de technologie et d'astronomie. Je travaille comme chef de projet IT chez Laval Europe.",
            competences = listOf("Gestion de projet", "Kotlin", "Git", "CI/CD", "Docker"),
            contact = "linkedin.com/in/jeevons-e-3660a7297",
            disponibilite = "Ouvert aux échanges sur de nouveaux projets"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilFullStackPreview() {
    ProfilDevTheme {
        EcranProfil(
            nom = "Camille Martin",
            role = "Développeuse full-stack",
            description = "Je travaille sur des applis web de A à Z, du back au front. J'aime quand le code est simple à relire.",
            competences = listOf("JavaScript", "React", "Node.js", "PostgreSQL"),
            contact = "camille.martin@exemple.fr",
            disponibilite = "Pas disponible en ce moment"
        )
    }
}
