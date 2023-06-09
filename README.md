# **Götterblick Notizen**

Einteilung in Module: Charakter, Meisterschirm, Wiki, Karte (Avespfade), Impressum

## **Charakter**

- Übersicht
    - Gruppieren
- Erstellen/Bearbeiten (Toggle Bearbeiten & Leveln)
    - Regelauswahl
    - Grundwerte (GP-Start, Eigenschaften Max)
    - Rasse, Kultur & Varianten
    - Professionen (BgB & Veteran)
    - Name, Alter, Erscheinungsbild
    - Vorauswahl (Verbilligt oder geschenkte Sachen)
    - Eigenschaften, Vor- & Nachteile, Sonderfertigkeiten & Zauber, Talente
    - Verbindungen
    - Einfacher Modus
        - Nachteile senken
        - Sonderfertigkeiten, Zauber und Liturgien kaufen
        - Eigenschaften und Talente Steigern
        - Kampfwerte verteilen
        - Inventar Managen
        - Spätweihe, Karmalqueste, Grosse Meditation
        - Abenteuer Eintragen

## **Meisterschirm**

- Hausregeln
- Gruppieren
- Verwalten der Gruppe (Leben, Ausdauer, Belastung usw.)
- Probenübersicht (Pro Char oder pro Talent. Modifikationen, Wahrscheinlichkeit)
    - Zauber, Fernkampf, Nahkampf, Liturgien mehr Erschwernisse
- NSC Generator & Gegner Generator
- Würfeltool (d6,d10, d20, d100)
- Kampfrundentool
- Alchemie, Beschwörung, Zauberzeichen, Schmiede, Kräutersuche, Jagd, Fischen Umrechen-, Kalender usw. Tool
- Basar
    - Items mit Preisen
    - Preismodifikation
    - Tiere (Mitsamt Ausbildung und dergleichen)
- Phex Codescheibe (Chiphre)
- ~~{[("Online")]}~~

- Color palette: https://colorhunt.co/palettes/pastel

## **Wiki**

- Rassen, Professionen und so weiter
- Index (wo zu finden, z.B. WdS s43ff)
    - PDF Link zum Regelwerk

## **Karte**

- Karte von Aventurien
- Städte, Dörfer, Siedlungen, Oasen
- Reiche, Politische Gebiete
- Strassen, Handelslinien
- Kraftlinien
- Routenberechnung (Avespfade)

## **Impressum**

- Rechtliche Absicherung
- Kontaktdaten (Probleme usw.)
- Lizenzen
- Ulisses

---

# Götterblick (arc42) - Introduction and Goals

The basic goal of this software/tool is to replace all previously existing tools and provide a solution that can handle
all tasks and functions while remaining in one tool. The exact functions are described above.

Inspiration for this software has been taken from the following, existing projects:

- [Meistergeister](https://meistergeister.org/)
- [Heldensoftware](https://www.helden-software.de/)
- [Heldenblatt](https://www.heldenblatt.ch/)
- [Optolith](https://optolith.app/de/)
- [Avespfade](https://avespfade.de/)
- [Dere-Globus](http://www.dereglobus.org/)

## Requirements Overview ##

### What is Götterblick? ### 

- A tool that contains a lot of useful tools for people playing the P&P DSA.
- This tool is supposed to aid either a player or a dungeon master in their adventure.
- Together with some exporting-features, this tool is supposed to be covering almost all tools that are currently
  seperated in the market.

### Main features: ###

- Character Editor:
    - Managing characters (create & edit)
    - Leveling up characters
    - Grouping characters in the overview
- Dungeon Master Screen:
    - Managing gaming sessions (create & edit)
    - Overview of npcs and enemies
    - Useful, smaller tools for QOL improvements
- Map of Aventuria:
    - All paths, cities and towns (and their names)
    - Sub-Maps of towns and cities
    - Calculators for easier traveling
- Wiki:
    - Research of enemies, races, items etc.

## Quality Goals

| Quality Goal           | Motivation and Explanation                                                                                                                                      |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Usability              | The tool is supposed to  to use. Users can easily navigate and create their desired scenarios.                                                                  |
| Compatability          | Given that there are multiple versions of DSA and other programs out there, we want to also include them, as to not restrict the preference of the user.        |
| Functional Suitability | The tool needs to function correctly by also acknowledging all rulebooks and calculate accordingly and correctly.                                               |
| Transferability        | The tool along with its data should be easily transferable from either Windows, Mac or Linux.                                                                   | 
| Maintainability        | Due to the different versions and homebrew rules of DSA the tools should be adaptable in their respective version for character creation and dungeon mastering. |

# Architecture Constraints

## Technical Constraints

| Constraint            | Explanation and Background                                                                                                                                                                             |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Coding Language: Java | The programm needs to be written in Java, due to the fact that both programmers want to write in / learn that language.                                                                                |
| Operating System      | Due to the fact, that this software is supposed to be usable on every operating system, special care needs to be taken when dealing with f. ex. filesystems or system-dependent environment variables. |
| JavaFX / No Swing     | To let the app appear a bit more modern, the app is supposed to be written entirely in JavaFX, not the 'old' Swing library.                                                                            |
| Licensing             | Some libraries have special licenses or agreements, which have to be taken into account, before using them. Thus special care needs to be taken, when importing dependencies from f. ex. Maven.        |

## Organisational Constraints

| Constraint             | Explanation and Background                                                                                                                                                              |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Planning with arc42    | Due to a portion of the coders wanting to learn / get into the field of development, we have chosen to work along the arc42 template to document and log process as well as plan in it. |
| IDE                    | To have a common development-ground, the developers are advised to use the IntelliJ Community Edition, along with the same formatter.                                                   |
| SceneBuilder           | To construct new windows for the tool, the software "SceneBuilder" is to be used, as the produced .xml files are helpful in the coding process.                                         |
| Version Control System | To keep track of changes and to allow work between multiple people on this project, GIT as a VCS is used and Github as the platform for the shared code.                                |
| JUnit Tests            | Testing the tool is mostly done in Java, more specifically in so called "JUnit Tests".                                                                                                  |
| Open Source Release    | If, and only if, "Ulisses Spiele" permits the development of this tool, then this software shall be open sourced and accessible for everyone.                                           |

## Conventional Constraints

| Constraint           | Explanation and Background                                                                                                                                                                                                                              |
|----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Good Coding Practice | To keep the code easy to read and maintable, we adhere to the good coding practices, as described [in this guide](https://www.developer.com/languages/javascript/java-best-practices/).                                                                 |
| Language             | The documentation language is supposed to be English, while the 'output' language / the interface is going to be German. This is due to the fact, that most players of DSA are German, while coding can still be done with English speaking developers. |

# System Scope and Context

## Business Context

<img src="src/site/pics/context/business-context.png"></img>

### Dungeonmaster (User) ###

The dungeon master utilizes the tool to manage and overview the group they are playing with. They also make use of the
wiki and map, to follow along in their P&P adventure. Occasionally, they also may create their own character or npc.

### Player (User) ###

The player is an attendant of a P&P group and usually / mostly utilizes the character-section and maybe the map and
wiki. The dungeon master screen is rarely or never used by them.

### Meistergeister (Third-Party System) ###

A similar tool like Götterblick but with a focus on the dungeon master, while the Götterblick software
includes both the user's expectations (f. ex. with exporting characters to VTT softwares) and also the dungeon master's.
With this connection, Götterblick is supposed to give the users the ability to still use "Meistergeister", if they wish
so.

### Heldensoftware (Third-Party System) ###

A character creation tool for DSA4.1. Characters from that tool should be importable into the Götterblick software, as
well as the other way around.

### FoundryVTT (Third-Party System) ###

FoundryVTT is a software for "virtual table top", meaning one can play with other users on a virtually a session / round
of P&P. With the multiple use cases and import as well as export features, the adaptation of that system is supposed to
be included, too.

### Optolith (Third-Party System) ###

This software is used as a DSA5 character creation tool. Characters from that tool can be exported to FoundryVTT or to a
file, so that another person can open them in their version of optolith. Their character-file is supposed to be also
editable in this Götterblick.

## Technical Context

<img src="src/site/pics/context/technical-context.png"></img>

### All Third-Party-Systems ###

As seen above, the third-party-systems are used to help / make it more comfortable for the user to import or export
characters to these specific softwares. This is done via their respective file format, thus the im- or export is done
via f. ex. .xml, .json or other formats produced by these softwares.

# Solution Strategy

# Building Block View

## Whitebox Overall System

***\<Overview Diagram>***

Motivation

:   *\<text explanation>*

Contained Building Blocks

:   *\<Description of contained building block (black boxes)>*

Important Interfaces

:   *\<Description of important interfaces>*

### \<Name black box 1> {#__name_black_box_1}

*\<Purpose/Responsibility>*

*\<Interface(s)>*

*\<(Optional) Quality/Performance Characteristics>*

*\<(Optional) Directory/File Location>*

*\<(Optional) Fulfilled Requirements>*

*\<(optional) Open Issues/Problems/Risks>*

### \<Name black box 2> {#__name_black_box_2}

*\<black box template>*

### \<Name black box n> {#__name_black_box_n}

*\<black box template>*

### \<Name interface 1> {#__name_interface_1}

...

### \<Name interface m> {#__name_interface_m}

## Level 2 {#_level_2}

### White Box *\<building block 1>* {#_white_box_emphasis_building_block_1_emphasis}

*\<white box template>*

### White Box *\<building block 2>* {#_white_box_emphasis_building_block_2_emphasis}

*\<white box template>*

...

### White Box *\<building block m>* {#_white_box_emphasis_building_block_m_emphasis}

*\<white box template>*

## Level 3 {#_level_3}

### White Box \<\_building block x.1\_\> {#_white_box_building_block_x_1}

*\<white box template>*

### White Box \<\_building block x.2\_\> {#_white_box_building_block_x_2}

*\<white box template>*

### White Box \<\_building block y.1\_\> {#_white_box_building_block_y_1}

*\<white box template>*

# Runtime View {#section-runtime-view}

## \<Runtime Scenario 1> {#__runtime_scenario_1}

- *\<insert runtime diagram or textual description of the scenario>*

- *\<insert description of the notable aspects of the interactions
  between the building block instances depicted in this diagram.\>*

## \<Runtime Scenario 2> {#__runtime_scenario_2}

## ... {#_}

## \<Runtime Scenario n> {#__runtime_scenario_n}

# Deployment View {#section-deployment-view}

## Infrastructure Level 1 {#_infrastructure_level_1}

***\<Overview Diagram>***

Motivation

:   *\<explanation in text form>*

Quality and/or Performance Features

:   *\<explanation in text form>*

Mapping of Building Blocks to Infrastructure

:   *\<description of the mapping>*

## Infrastructure Level 2 {#_infrastructure_level_2}

### *\<Infrastructure Element 1>* {#__emphasis_infrastructure_element_1_emphasis}

*\<diagram + explanation>*

### *\<Infrastructure Element 2>* {#__emphasis_infrastructure_element_2_emphasis}

*\<diagram + explanation>*

...

### *\<Infrastructure Element n>* {#__emphasis_infrastructure_element_n_emphasis}

*\<diagram + explanation>*

# Cross-cutting Concepts {#section-concepts}

## *\<Concept 1>* {#__emphasis_concept_1_emphasis}

*\<explanation>*

## *\<Concept 2>* {#__emphasis_concept_2_emphasis}

*\<explanation>*

...

## *\<Concept n>* {#__emphasis_concept_n_emphasis}

*\<explanation>*

# Architecture Decisions {#section-design-decisions}

# Quality Requirements {#section-quality-scenarios}

## Quality Tree {#_quality_tree}

## Quality Scenarios {#_quality_scenarios}

# Risks and Technical Debts {#section-technical-risks}

# Glossary {#section-glossary}

| Term        | Definition        |
|-------------|-------------------|
| *\<Term-1>* | *\<definition-1>* |
| *\<Term-2>* | *\<definition-2>* |

