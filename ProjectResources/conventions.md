# Coding Conventions

## Indentation
4 Spaces

## Constants
ALL_CAPS_SNAKECASE

## Methods and Variables
camelCase

## Classes
PascalCase

## Method Modifiers
1. public/protected/private
2. abstract/default/static/final
3. sychronized/native/strictfp/transient/volatile (Only use these if theres a really really good reason)

## Encapsulation
All class variables should be private unless good reason otherwise is found.

## Packages
* org.CandyLand.model.* or org.CandyLand.view.* or org.CandyLand.* if not a model or view
  * test packages should be formed as org.CandyLand.tests.(model/view/_nothing_).*

## File Structure
* Source Files: src/main/java/_package_/*
* Resource Files (Such as images): src/main/resources/_Filetype folder (ex: Images)_/*
* Test Files: src/test/java/_package_/* (Ideally Test Files Should Match Source Naming)