// In Android Studio, add the following dependency to your build.gradle.kts file:
// implementation("com.google.ai.client.generativeai:generativeai:0.1.1")

// Add the following code to your Kotlin source code
import com.google.ai.client.generativeai.GenerativeModel

val model = GenerativeModel(
        "gemini-1.0-pro",
        // Retrieve API key as an environmental variable defined in a Build Configuration
        // see https://github.com/google/secrets-gradle-plugin for further instructions
        BuildConfig.apiKey,
        generationConfig = generationConfig {
            temperature = 0.9f
            topK = 1
            topP = 1f
            maxOutputTokens = 2048
        },
        safetySettings = listOf(
                SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE),
                SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE),
        ),
)

val chatHistory = listOf(
)

val chat = model.startChat(chatHistory)
val response = chat.sendMessage("YOUR_USER_INPUT")

// Get the first text part of the first candidate
print(response.text)
// Alternatively
print(response.candidates.first().content.parts.first().asTextOrNull())