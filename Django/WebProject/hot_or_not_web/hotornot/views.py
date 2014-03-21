## Create your views here.
import json
from django.shortcuts import render_to_response
from django.template import RequestContext
from models import Dayofweekhits3, Venuehits3, Venuecompleteinformation, Dayofweekhits, Venuehits
from sets import Set
from pickle import APPEND

def base(request):
    context = RequestContext(request)
 
#    jsonData = {
#           "o2 Academy,club" : {
#                "Mon" : 105, "Tue" : 123, "Wed" : 234, "Thu" : 23, "Fri" : 325, "Sat" : 114, "Sun": 156
#            },
#           "University of Glasgow,university" : {
#                "Mon" : 566, "Tue" : 65, "Wed" : 45, "Thu" : 27, "Fri" : 52, "Sat" : 65, "Sun": 123
#            },
#            "Firhill Stadium,stadium" : {
#                "Mon" : 637, "Tue" : 234, "Wed" : 262, "Thu" : 266, "Fri" : 677, "Sat" : 45, "Sun": 657
#            }
#    }
    
         
    
    return render_to_response('home/statistics.html', get_data(''), context)

def about(request):
    context = RequestContext(request)
    
    
    return render_to_response('home/about.html', {}, context)

def top_five(request):
    context = RequestContext(request)
    category = request.GET.get('cat', '');
    
    return render_to_response('home/top5.html', get_data(category), context)

def venue(request):
    context = RequestContext(request)
    venueName = request.GET.get('name', '');
    category = request.GET.get('type', '');
    context_dict = {}    
    venueInfo = Venuecompleteinformation.objects.get(name = venueName, type = category)

    context_dict['venueObject'] = venueInfo
    
    return render_to_response('home/venue_page.html', context_dict, context)


def get_data(category):
    #returns context dic [jsonData, venues, categories], where jsonData is complex data structure and venues is list of venue names
    context_dict = {}
    jsonData = {}
    venues = []
    categoriesSet = Set()
    
    if category == '':
        DayofweekhitsObjects = Dayofweekhits3.objects.all()
        VenuehitsObjects = Venuehits3.objects.all()
        for v in DayofweekhitsObjects:
            if v.name == '':
                continue
            
            vDic = {}
            name_type = v.name + ',' + v.type        
            if name_type in jsonData:
                vDic = jsonData[name_type]
                
            vDic[v.weekday] = v.hits
            jsonData[name_type] = vDic
    else:
        DayofweekhitsObjects = []      
        VenuehitsObjects = Venuehits3.objects.all().filter(type = category).order_by('-total_hits', '-celebrity_hits')[:5]

        for v in VenuehitsObjects:
            DayofweekhitsObjects.append(Dayofweekhits3.objects.all().filter(name = v.name).filter(type = category))
        
        for object in DayofweekhitsObjects:
            for v in object:
                if v.name == '':
                    continue
                
                vDic = {}
                name_type = v.name + ',' + v.type        
                if name_type in jsonData:
                    vDic = jsonData[name_type]
                    
                vDic[v.weekday] = v.hits
                jsonData[name_type] = vDic
    
    
      
    
    for v in VenuehitsObjects:
        if v.name == '':
            continue
        vDic = {}
        name_type = v.name + ',' + v.type
        if name_type in jsonData:
            jsonData[name_type]['total_hits'] = v.total_hits
            jsonData[name_type]['celebrity_hits'] = v.celebrity_hits
        else:
            vDic['total_hits'] = v.total_hits
            vDic['celebrity_hits'] = v.celebrity_hits
            jsonData[name_type] = vDic
        
        venues.append(v)
        categoriesSet.add(v.type);
        
    
    
    if '' in categoriesSet:   
        categoriesSet.remove('')  
         
    context_dict['categories'] = categoriesSet
    context_dict['venues'] = venues  
    context_dict['json'] = json.dumps(jsonData)
    
    return context_dict
    
    
    
    
    
    
