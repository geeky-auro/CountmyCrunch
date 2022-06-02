package com.aurosaswatraj.countmycrunch.HealthBlogs

class HealthVlogs(val image:String, val title:String, val description:String, val vlogLink:String) {



    fun getMimgae(): String {
        return image
    }

    fun getMtitle():String{
        return title
    }
    fun getMDescription():String{
        return description
    }

    fun getMVlogLink():String{
        return vlogLink
    }



   /**
    * vlogs.add(HealthVlogs("https://artofhealthyliving.com/wp-content/uploads/2022/05/custom-orthotics-to-help-with-foot-pain.jpg","The Art of Healthy Living","ur feet perform a heavy workload daily. And being squeezed into tight shoes doesn’t help either. Shouldering your weight around day and night, it’s a relief to them when you head to bed at night, and they finally get to rest. Your feet literally say “aah!”","https://artofhealthyliving.com/wearing-these-pillowy-custom-orthotics-your-feet-will-say-aah/"))
    * vlogs.add(HealthVlogs("https://artofhealthyliving.com/wp-content/uploads/2022/05/Healthy-Living-The-Benefits-Of-Water-Exercise.jpg","HEALTHY LIVING: THE BENEFITS OF WATER EXERCISE","Short description of the article","https://artofhealthyliving.com/wearing-these-pillowy-custom-orthotics-your-feet-will-say-aah/"))
    *
    * */

}