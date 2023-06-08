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

# Götterblick - arc42

# Introduction and Goals

The basic goal of this software/tool is to replace all previously existing tools and provide a solution that can handle
all tasks and functions while remaining in one tool. The exact functions are described above.

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

# System Scope and Context

## Business Context

**\<Diagram or Table>**

**\<optionally: Explanation of external domain interfaces>**

## Technical Context

**\<Diagram or Table>**

**\<optionally: Explanation of technical interfaces>**

**\<Mapping Input/Output to Channels>**

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

