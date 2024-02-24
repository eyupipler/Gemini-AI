// In the Xcode Add Packages dialog, add this Swift package URL into the search bar:
// https://github.com/google/generative-ai-swift

// Add the following code to your Swift source code
import GoogleGenerativeAI

let config = GenerationConfig(
  temperature: 0.9,
  topP: 1,
  topK: 1,
  maxOutputTokens: 2048,
)

// Don't check your API key into source control!
guard let apiKey = ProcessInfo.processInfo.environment["API_KEY"] else {
  fatalError("Add `API_KEY` as an Environment Variable in your app's scheme.")
}

let model = GenerativeModel(
  name: "gemini-1.0-pro",
  apiKey: apiKey,
  generationConfig: config,
  safetySettings: [
    SafetySetting(harmCategory: .harassment, threshold: .blockMediumAndAbove),
    SafetySetting(harmCategory: .hateSpeech, threshold: .blockMediumAndAbove),
    SafetySetting(harmCategory: .sexuallyExplicit, threshold: .blockMediumAndAbove),
    SafetySetting(harmCategory: .dangerousContent, threshold: .blockMediumAndAbove)
  ]
)

let chat = model.startChat(history: [

])

Task {
  do {
    let message = "YOUR_USER_INPUT"
    let response = try await chat.sendMessage(message)
    print(response.text ?? "No response received")
  } catch {
    print(error)
  }
}