package edu.bloomu.bipolarsymptomtracker.ui.theme

object Strings {
    object SharedPrefKeys {
        val sharedPrefs = "app_preferences"
        val cycleLength = "cycle_length"
        val setupCompleted = "setup"
        val userName = "username"
    }
    object Screens {
        object Welcome {
            val screenTitle = "Welcome"
            val blurb = "Become a different kind of perfect with "
            val disclaimer = "This app is for entertainment purposes only. Consult with a physician before making healthcare decisions. Don't die. Idiot."
            val nameEntry = "Your name"
            val cycleLength = "Your average episode length"
            val submitButton = "Let's get started"
            val titlePrefix = "Hello,"
            val titleHighlight = "you"
        }
        object Analysis {
            val screenTitle = "Your analysis"
            val greetingPrefix = "Howdy,"
            val currStatePrefix = "Your current state is "
            val cyclePosPrefix = "You're currently"
            val cyclePosSuffix = "in your cycle"
            val thisLengthPrefix = "You've been in this state for"
            val avgLengthPrefix = "normally"
            object MedDialog {
                val title = "It looks like you haven't been taking your meds recently"
                val body = "Missing your meds can impact you a lot. " +
                        "If you think your meds aren't working, talk to your doctor."
            }
            object DrugDialog {
                val title = "It looks like you've been using drugs recently"
                val body = "Drug use will have a bigger impact on you than it would somebody else " +
                        "and can impact your state a lot, making you erratic or very intensely " +
                        "emotional. Avoid using drugs not prescribed by your doctor (including " +
                        "alcohol and caffeine)"
            }
        }
        object Entries {
            val screenTitle = "Your entries"
            object Error {
                val heading = "Uh oh..."
                val subheading = "Looks like you haven't logged any entries yet"
                val hint = "Click here to get started with your first entry!"
                val iconAlt = "A grimacing cartoon face"
            }
            object Delete {
                object ConfirmDialog {
                    val title = "Are you sure?"
                    val message = "This action cannot be undone"
                    val confirmButton = "Delete entry"
                    val cancelButton = "Cancel"
                }
                object SuccessDialog {
                    val title = "Entry deleted"
                    val body = "Entry has been deleted successfully"
                }
            }
        }
        object EditEntry {
            val screenTitle = "Your analysis"
            val drugSwitch = "I took an illicit drug today"
            val medsSwitch = "I missed my meds today"
        }
        object Settings {
            val screenTitle = "Settings"
            val clearEntries = "Clear entries"
            val name = "Update your name"
            val cycleLength = "Adjust your cycle length"
            val about = "About"
            object ConfirmDialog {
                val title = "Are you sure?"
                val message = "This action cannot be undone"
                val confirmButton = "Yes, clear all my entries"
                val cancelButton = "No, don't clear my entries"
            }
            object SuccessDialog {
                object SaveSettings {
                    val title = "Settings saved"
                    val body = "Settings have been saved successfully"
                    val dismiss = "Okay"
                }
                object ClearEntries {
                    val title = "Entries cleared"
                    val body = "Entries have been cleared successfully"
                    val dismiss = "Okay"
                }
            }
        }
    }
    object Model {
        object CyclePositions {
            val start = " in the beginning  "
            val middle = " in the middle  "
            val end = " at the end  "
            val unknown = " at an unknown point  "
        }
        object States {
            object Manic {
                val title = "Manic"
                val blurb = "You're in an intense manic state"
                val imageAlt = "Manic face"
                val body = "Hey! Hey! Calm down! You're not king, queen, and/or themperor of the universe right now. Everything seems like a good idea, but you need to take extra time to think about your actions! A lot of extra time! Contact your doctor or dial 988 if your symptoms get severe enough."
            }
            object HypoManic {
                val title = "Hypo-Manic"
                val blurb = "You're in a mild manic state"
                val imageAlt = "Hypo-manic face"
                val body = "You might be feeling pretty good right now. Everything finally seems like it's looking up! Take extra care before you make big decisions and make sure your friends know to tell you if you start behaving oddly."
            }
            object Depressed {
                val title = "Depressed"
                val blurb = "You're in an intense depressive state"
                val imageAlt = "Depressed face"
                val body = "Keep your head up! Make sure to keep up with the little things, like personal hygiene and eating. If you have any dark thoughts, making sure to talk to your doctor immediately, go to the E.R., dial 988 for mental health services or dial 911 for emergency services."
            }
            object HypoDepressed {
                val title = "Hypo-Depressed"
                val blurb = "You're in a mild depressive state"
                val imageAlt = "Hypo-depressed face"
                val body = "You may be feeling sad, alone, bad about yourself, or any manner of nasty things right now. Don't let the dark thoughts get at you. They're not real!"
            }
            object Unknown {
                val title = "Unknown"
                val blurb = "There isn't quite enough data to get an analysis on you"
                val imageAlt = "Unknown face"
                val body = "Once you log a couple of entries, an analysis will be available here"
            }
            object Unstable {
                val title = "Unstable"
                val blurb = "You're a little all over the place right now"
                val imageAlt = "Unstable face"
                val body = "You need to be extra critical of your moods these past few days and weeks. If you or your loved ones think you're acting very erratically, you should talk to your doctor immediately."
            }
            object Neutral {
                val title = "Neutral"
                val blurb = "You seem fine"
                val imageAlt = "Neutral face"
                val body = "Yeah, kinda. I mean, you still have to be vigilant, but... You're doing a good job!"
            }
        }
        object Symptoms {
            object Mania {
                object Temper {
                    val title = "Irritable or short tempered"
                    val body = "You're getting angry really easily, having outbursts, yelling, screaming, etc."
                }
                object Energy {
                    val title = "Very energetic"
                    val body = "You have more energy than you normally do, like if you drank a lot of caffeine"
                }
                object Ego {
                    val title = "Inflated self worth"
                    val body = "You're feeling much better about yourself lately or like you're better than others"
                }
                object Sleep {
                    val title = "Sleeping less or needing less sleep"
                    val body = "You're sleeping a lot less than you normally do, waking up early, and feeling less tired with less sleep"
                }
                object Speech {
                    val title = "Talking a lot or speaking quickly"
                    val body = "You're speaking so quickly that others can't keep up well or you're talking a lot more than you usually do"
                }
                object Racing {
                    val title = "Racing thoughts"
                    val body = "Your thoughts are all over the place, changing quickly"
                }
                object Distract {
                    val title = "Easily distracted"
                    val body = "You find yourself more easily distracted than normal or distracted by things that usually wouldn't distract you"
                }
                object Reckless {
                    val title = "Acting recklessly"
                    val body = "You're doing things without giving them much thought or doing things that are dangerous or harmful (like speeding down the highway)"
                }
                object Sex {
                    val title = "Increased sex drive"
                    val body = "You're hornier than usual and pursuing sex, having sex, or masturbating more aggressively and/or more often than normal"
                }
                object Money {
                    val title = "Spending a lot of money"
                    val body = "You're making a lot of ill-advised financial decisions or made/want to make a few large very stupid money moves"
                }
            }
            object Depression {
                object Motivation {
                    val title = "Low or no motivation to do things"
                    val body = "You don't feel like getting out of bed or getting anything done"
                }
                object Hobbies {
                    val title = "Hobbies are boring or uninteresting"
                    val body = "You don't really find joy or satisfaction in things you usually like doing"
                }
                object Sleep {
                    val title = "Sleeping more"
                    val body = "You're taking more naps during the day, going to bed earlier, or sleeping later"
                }
                object Restless {
                    val title = "Feeling restless"
                    val body = "You don't really feel comfortable anywhere or doing anything, like you want to always be doing something else no matter what"
                }
                object Slow {
                    val title = "Moving more slowly"
                    val body = "You're getting less done because you're physically moving more slowly than normal or you're taking more time to do things"
                }
                object Energy {
                    val title = "Feeling very low energy or exhausted"
                    val body = "You constantly feel like it's the end of a long day or like you didn't get enough sleep"
                }
                object Think {
                    val title = "Trouble thinking clearly"
                    val body = "Your thoughts are cloudy or you're having trouble processing things"
                }
                object Concentrate {
                    val title = "Trouble concentrating"
                    val body = "You can't really concentrate on anything, like your brain just wants to do nothing"
                }
                object Suicidal {
                    val title = "Feeling suicidal or wanting to self harm"
                    val body = "You feel like you want to hurt yourself, kill yourself, or you have been self-harming"
                }
                object Sex {
                    val title = "Decreased or no sex drive"
                    val body = "You're feeling increasingly asexual lately, like you don't want to have sex and/or it's less pleasurable when you do have it"
                }
            }
        }
    }
    object Components {
        object UsernameField { val placeholder = "Your name" }
        object DeleteButton { val iconAlt = "Delete" }
        object Dialogs {
            object Success { val dismiss = "Okay" }
            object Confirm {
                val title = "Are you sure?"
                val message = "This action can't be undone"
                val confirm = "Okay"
                val cancel = "Cancel"
            }
        }
        object Scaffold {
            object BottomAppBar {
                object Buttons {
                    object Analysis {
                        val name = "Analysis"
                        val iconAlt = ""
                    }
                    object Entries {
                        val name = "Entries"
                        val iconAlt = ""
                    }
                }
            }
            object TopAppBar {
                object Buttons {
                    object Settings {
                        val name = ""
                        val iconAlt = "Settings"
                    }
                }
            }
            object Fab {}
        }
    }
}