package ru.vsokolova.volumetric_table.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase


@Database(entities = [Volume::class], version = 1, exportSchema = false)
abstract class VolumeDatabase : RoomDatabase() {
    abstract fun getVolumeDao(): VolumeDao

    companion object {
        @Volatile
        private var INSTANCE: VolumeDatabase? = null

        fun getDatabase(context: Context): VolumeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    VolumeDatabase::class.java,
                    "volumetric_table"
                )
                    .createFromAsset("volume_table.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}


//private fun readEntry(parser: XmlPullParser): Volume {
//    parser.require(XmlPullParser.START_TAG, ns, "entry")
//    var length: String
//    var thick: String
//    var top: Short
//    var value: String
//    while (parser.next() != XmlPullParser.END_TAG) {
//        if (parser.eventType != XmlPullParser.START_TAG) {
//            continue
//        }
//        when (parser.name) {
//            "length" -> length = readLength(parser)
//            "summary" -> summary = readSummary(parser)
//            "link" -> link = readLink(parser)
//            else -> skip(parser)
//        }
//    }
//    return Volume(0, length, summary, link)
//}