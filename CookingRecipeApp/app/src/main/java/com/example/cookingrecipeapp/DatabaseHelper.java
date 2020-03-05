package com.example.cookingrecipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    public DatabaseHelper(Context context) {
        super(context, "Recipe", null, 1);

    }

    public void onCreate(SQLiteDatabase db) {
        String sqlCategory = "CREATE TABLE category" +
                "(categoryName varchar(50) primary key);";
        db.execSQL(sqlCategory);

        String sqlRecipe = "CREATE TABLE recipe" +
                "(recipeName TEXT primary key ," +
                " categoryN varchar(50) ," +
                "foreign key(categoryN) references category(categoryName) );";
        db.execSQL(sqlRecipe);

        String sqlIngredients = "CREATE TABLE ingredients" +
                "(ingredient TEXT ," +
                "recipeName TEXT," +
                "foreign key(recipeName) references recipe(recipeName)," +
                "primary key (ingredient,recipeName)" +
                ");";
        db.execSQL(sqlIngredients);

        String sqlInstructions = "CREATE TABLE instructions" +
                "(instructions TEXT ," +
                "recipeName TEXT," +
                "foreign key(recipeName) references recipe(recipeName)," +
                "primary key (instructions,recipeName)" +
                ");";
        db.execSQL(sqlInstructions);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    public void close() {
        if (database != null)
            database.close();
    }

    public boolean addCategory(String name) {
        open();
        ContentValues newCategory = new ContentValues();
        newCategory.put("categoryName", name);
        long result = database.insert("category", null, newCategory);
        return (result == -1) ? false : true;
    }
    public void cleanDb(){
        open();
        database.delete("recipe",null,null);

    }

    public boolean addRecipe(String name, String category) {
        open();
        ContentValues newCategory = new ContentValues();
        newCategory.put("categoryN", category);
        newCategory.put("recipeName", name);
        long result = database.insert("recipe", null, newCategory);
        return (result == -1) ? false : true;
    }

    public boolean addIngrediant(String recipeName, String ingrediand) {
        open();
        ContentValues newCategory = new ContentValues();
        newCategory.put("recipeName", recipeName);
        newCategory.put("ingredient", ingrediand);
        long result = database.insert("ingredients", null, newCategory);

        return (result == -1) ? false : true;
    }


    public boolean addInstruction(String recipeName, String Instructor) {
        open();
        ContentValues newCategory = new ContentValues();
        newCategory.put("recipeName", recipeName);
        newCategory.put("instructions", Instructor);
        long result = database.insert("instructions", null, newCategory);

        return (result == -1) ? false : true;
    }
    public Cursor getCategory(String a){
        open();
        return database.rawQuery("SELECT B.categoryN FROM recipe as B WHERE B.recipeName = \"" +a+"\"", null);
    }

    public Cursor getAllCateGories() {
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM category", null);
        return cursor;
    }

    public Cursor getAllRecipe() {
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM recipe", null);
        return cursor;
    }


    public void deleteRecipe(String recipeName) {
        open();
        database.delete("recipe", "recipeName= \"" + recipeName+"\"", null);

    }
    public boolean deleteIngredient(String recipeName,String ingredient){
            open();
            database.delete("ingredients","recipeName= \"" + recipeName+"\" and ingredient = \""+ingredient+"\"",null);
            return true;

    }
    public boolean deleteInstruction(String recipeName,String instructions){
        open();
        database.delete("instructions","recipeName= \"" + recipeName+"\" and instructions = \""+instructions+"\"",null);
        return true;

    }

    public boolean updateIngredient(String recipeName, String ingredient,String oldIng){
        open();
        ContentValues updateIng = new ContentValues();
        updateIng.put("recipeName", recipeName);
        updateIng.put("ingredient", ingredient);
        database.update("ingredients", updateIng, "recipeName= \"" + recipeName+"\" and ingredient = \""+oldIng+"\"", null);
        return true;
    }
    public boolean updateInstruction(String recipeName, String instruction,String oldIns){
        open();
        ContentValues updateIng = new ContentValues();
        updateIng.put("recipeName", recipeName);
        updateIng.put("instructions", instruction);
        database.update("instructions", updateIng, "recipeName= \"" + recipeName+"\" and instructions = \""+oldIns+"\"", null);
        return true;
    }

    public Cursor getIngredients(String RecipeName) {
        open();
        return database.rawQuery("Select A.ingredient From ingredients as A Where A.recipeName = \"" + RecipeName+"\"",null);
    }

    public Cursor getInstructions(String RecipeName) {
        open();
        return database.rawQuery("Select A.instructions From instructions as A Where A.recipeName = \"" + RecipeName+"\"", null);
    }


}

