package com.pt29.crypto.Models

import io.realm.RealmObject
import io.realm.annotations.RealmClass


/**
 * Created by parham on 3/3/2018.
 */
@RealmClass
open class WidgetModel : RealmObject() {
     var appWidgetId: Int = 0
     var coinID: String = ""


}