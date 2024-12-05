package edu.bloomu.bipolarsymptomtracker.ui.theme

object Strings {
    object Symptoms {
        object Mania {
            object Temper {
                val Title = "Irritable or short tempered"
                val Desc = "You're getting angry really easily, having outbursts, yelling, screaming, etc."
            }
            object Energy {
                val Title = "Very energetic"
                val Desc = "You have more energy than you normally do, like if you drank a lot of caffeine"
            }
            object Ego {
                val Title = "Inflated self worth"
                val Desc = "You're feeling much better about yourself lately or like you're better than others"
            }
            object Sleep {
                val Title = "Sleeping less or needing less sleep"
                val Desc = "You're sleeping a lot less than you normally do, waking up early, and feeling less tired with less sleep"
            }
            object Speech {
                val Title = "Talking a lot or speaking quickly"
                val Desc = "You're speaking so quickly that others can't keep up well or you're talking a lot more than you usually do"
            }
            object Racing {
                val Title = "Racing thoughts"
                val Desc = "Your thoughts are all over the place, changing quickly"
            }
            object Distract {
                val Title = "Easily distracted"
                val Desc = "You find yourself more easily distracted than normal or distracted by things that usually wouldn't distract you"
            }
            object Reckless {
                val Title = "Acting recklessly"
                val Desc = "You're doing things without giving them much thought or doing things that are dangerous or harmful (like speeding down the highway)"
            }
            object Sex {
                val Title = "Increased sex drive"
                val Desc = "You're hornier than usual and pursuing sex, having sex, or masturbating more aggressively and/or more often than normal"
            }
            object Money {
                val Title = "Spending a lot of money"
                val Desc = "You're making a lot of ill-advised financial decisions or made/want to make a few large very stupid money moves"
            }
        }
        object Depression {
            object Motivation {
                val Title = "Low or no motivation to do things"
                val Desc = "You don't feel like getting out of bed or getting anything done"
            }
            object Hobbies {
                val Title = "Hobbies are boring or uninteresting"
                val Desc = "You don't really find joy or satisfaction in things you usually like doing"
            }
            object Sleep {
                val Title = "Sleeping more"
                val Desc = "You're taking more naps during the day, going to bed earlier, or sleeping later"
            }
            object Restless {
                val Title = "Feeling restless"
                val Desc = "You don't really feel comfortable anywhere or doing anything, like you want to always be doing something else no matter what"
            }
            object Slow {
                val Title = "Moving more slowly"
                val Desc = "You're getting less done because you're physically moving more slowly than normal or you're taking more time to do things"
            }
            object Energy {
                val Title = "Feeling very low energy or exhausted"
                val Desc = "You constantly feel like it's the end of a long day or like you didn't get enough sleep"
            }
            object Think {
                val Title = "Trouble thinking clearly"
                val Desc = "Your thoughts are cloudy or you're having trouble processing things"
            }
            object Concentrate {
                val Title = "Trouble concentrating"
                val Desc = "You can't really concentrate on anything, like your brain just wants to do nothing"
            }
            object Suicidal {
                val Title = "Feeling suicidal or wanting to self harm"
                val Desc = "You feel like you want to hurt yourself, kill yourself, or you have been self-harming"
            }
            object Sex {
                val Title = "Decreased or no sex drive"
                val Desc = "You're feeling increasingly asexual lately, like you don't want to have sex and/or it's less pleasurable when you do have it"
            }
        }
    }
    object StateText {
        object Manic {
            val Title = "Manic"
            val Desc = "You're in an intense manic state"
            val ImageDesc = "Manic face"
            val Body = "Hey! Hey! Calm down! You're not king, queen, and/or themperor of the universe right now. Everything seems like a good idea, but you need to take extra time to think about your actions! A lot of extra time! Contact your doctor or dial 988 if your symptoms get severe enough."
        }
        object HypoManic {
            val Title = "Hypo-Manic"
            val Desc = "You're in a mild manic state"
            val ImageDesc = "Hypo-manic face"
            val Body = "You might be feeling pretty good right now. Everything finally seems like it's looking up! Take extra care before you make big decisions and make sure your friends know to tell you if you start behaving oddly."
        }
        object Depressed {
            val Title = "Depressed"
            val Desc = "You're in an intense depressive state"
            val ImageDesc = "Depressed face"
            val Body = "Keep your head up! Make sure to keep up with the little things, like personal hygiene and eating. If you have any dark thoughts, making sure to talk to your doctor immediately, go to the E.R., dial 988 for mental health services or dial 911 for emergency services."
        }
        object HypoDepressed {
            val Title = "Hypo-Depressed"
            val Desc = "You're in a mild depressive state"
            val ImageDesc = "Hypo-depressed face"
            val Body = "You may be feeling sad, alone, bad about yourself, or any manner of nasty things right now. Don't let the dark thoughts get at you. They're not real!"
        }
        object Unknown {
            val Title = "Unknown"
            val Desc = "There isn't quite enough data to get an analysis on you"
            val ImageDesc = "Unknown face"
            val Body = "Once you log a couple of entries, an analysis will be available here"
        }
        object Unstable {
            val Title = "Unstable"
            val Desc = "You're a little all over the place right now"
            val ImageDesc = "Unstable face"
            val Body = "You need to be extra critical of your moods these past few days and weeks. If you or your loved ones think you're acting very erratically, you should talk to your doctor immediately."
        }
        object Neutral {
            val Title = "Neutral"
            val Desc = "You seem fine"
            val ImageDesc = "Neutral face"
            val Body = "Yeah, kinda. I mean, you still have to be vigilant, but... You're doing a good job!"
        }
    }
    object AnalysisScreenText {
        val Greeting = "Howdy, "
        val YourState = "Your current state is "
        val CyclePositionPrefix = "You're currently "
        val CyclePositionSuffix = " in your cycle"
        val CycleLengthPrefix = "You've been in this state for "
        val CycleLengthSuffix = " days"
    }
    object WelcomeText {
        val Blurb = "Become a different kind of perfect with "
        val Disclaimer = "This app is for entertainment purposes only. Consult with a physician before making healthcare decisions. Don't die. Idiot."
        val NameEntry = "Your name"
        val CycleLength = "Your average episode length"
        val ButtonText = "Let's get started"
    }
    object SharedPrefKeys {
        val SharedPreferences = "app_preferences"
        val CycleLength = "cycle_length"
        val SetupCompleted = "setup"
        val UserName = "username"
    }
    object Settings {
        val ClearEntries = "Clear entries"
        val Name = "Update your name"
        val CycleLength = "Adjust your cycle length"
        val About = "About"
        object ConfirmClearDialog {
            val Title = "Are you sure?"
            val Message = "This action cannot be undone"
            val ConfirmText = "Yes, clear all my entries"
            val CancelText = "No, don't clear my entries"
        }
        object SuccessDialog {
            object SaveSettings {
                val Title = "Settings saved"
                val Body = "Settings have been saved successfully"
                val Button = "Okay"
            }
            object ClearEntries {
                val Title = "Entries cleared"
                val Body = "Entries have been cleared successfully"
                val Button = "Okay"
            }
        }
    }
    object ScreenTitles {
        val Welcome = ""
        val Entries = "Your entries"
        val EntryScreen = "Entry"
        val Settings = "Settings"
        val Analysis = "Your analysis"
    }
    object NavigationBar {
        val Analysis = "Analysis"
        val Entries = "Entries"
    }
    object EntriesScreen {
        object NoEntriesError {
            val Heading = "Uh oh..."
            val Subheading = "Looks like you haven't logged any entries yet"
            val ClickHere = "Click here to get started with your first entry!"
            val IconDesc = "A grimacing cartoon face"
        }
    }
    object SingleEntryScreen {
        val DrugSwitch = "I took an illicit drug today"
        val MedsSwitch = "I took my meds today"
    }
    object Scaffold {
        object BottomAppBar {
            object Buttons {
                object Analysis {
                    val Text = "Analysis"
                    val IconDesc = ""
                }
                object Entries {
                    val Text = "Entries"
                    val IconDesc = ""
                }
            }
        }
        object TopAppBar {
            object Buttons {
                object Settings {
                    val Text = ""
                    val IconDesc = "Settings"
                }
            }
        }
        object Fab {
            val AddEntry = "Add entry"
            val SaveEntry = "Save entry"
        }
    }
}