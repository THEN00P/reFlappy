# andEngine

AndEngine library packed for Android Studio. The original AndEngine project was created by Nicholas Gramlich and can be found on [AndEngine.org](http://www.andengine.org/).

## What it contains
- *AndEngine* source with all the basic functionalities
- *TexturePackerLibrary* that allows to use spritesheets with [TexturePacker](https://www.codeandweb.com/texturepacker)
- *PhysicsBox2D* extension that includes physics functionalities
- *PhysicsEditorShapeLibrary* that allows to generate body shapes and give physics properties with [PhysicsEditor](https://www.codeandweb.com/physicseditor)
- *DebugDrawExtension* that allows to visualize bodies' outlines for debugging purposes (property of [nazgee](https://github.com/nazgee/AndEngineDebugDrawExtension))

## How to install

1. Clone this repo in the root of your Android Studio project folder.

2. Open _settings.gradle_ file of your project and add `include ':andEngine'`

3. Open _build.gradle_ module of your project and add `compile project(path: ':andEngine')` as the last line inside the *dependencies* object.

4. Sync the project.
