package com.ammyt.domain.interactor.getallactivities

import android.content.Context
import android.util.Log
import com.ammyt.domain.interactor.ErrorCompletion
import com.ammyt.domain.interactor.SuccessCompletion
import com.ammyt.domain.model.Activities
import com.ammyt.domain.model.Activity
import com.ammyt.domain.model.Shop
import com.ammyt.domain.model.Shops
import com.ammyt.madridshops.repository.Repository
import com.ammyt.madridshops.repository.RepositoryImpl
import com.ammyt.madridshops.repository.model.ActivityEntity
import com.ammyt.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetAllActivitiesInteractorImpl(context: Context) : GetAllActivitiesInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion) {
        repository.getAllActivities(success = {
            val activities: Activities = entityMapper(it)
            success.successCompletion(activities)
        }, error = {
            error.errorCompletion(it)
        })
    }

    private fun entityMapper(list: List<ActivityEntity>): Activities {
        val activitiesList = ArrayList<Activity>()

        list.forEach {
            activitiesList.add(mapActivityEntityToActivity(it))
        }

        return Activities(activitiesList)
    }

    private fun mapActivityEntityToActivity(activityEntity: ActivityEntity): Activity {
        return Activity(
                activityEntity.id.toInt(),
                activityEntity.name,
                activityEntity.description_en,
                activityEntity.description_es,
                parseStringToDouble(activityEntity.latitude),
                parseStringToDouble(activityEntity.longitude),
                activityEntity.img,
                activityEntity.logo,
                activityEntity.openingHours_en,
                activityEntity.openingHours_es,
                activityEntity.address,
                activityEntity.telephone,
                activityEntity.url
        )
    }

    private fun parseStringToDouble(value: String): Double? {
        var coordinate: Double? = null

        val parsedString: String = value.replace(",", "").replace(" ", "")

        try {
            coordinate = parsedString.toDouble()
        } catch (e: Exception) {
            Log.d("PARSE ERROR", "💩 Error parsing to float: " + value)
        }

        return coordinate
    }
}