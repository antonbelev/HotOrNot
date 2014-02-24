## Create your views here.
import json
from django.shortcuts import render_to_response
from django.template import RequestContext
from models import Dayofweekhits, Venuehits
from sets import Set

def base(request):
    context = RequestContext(request)
    context_dict = {}
    
    #read venues from db here
#    venues = ['o2 Academy', 'Firhill Stadium', 'University of Glasgow']
#    years = ["2009", "2010", "2011"];
    
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
    
    jsonData = {}
    venues = []
    categoriesSet = Set()
#    counter = 0
    
    print Dayofweekhits.objects.all().order_by('hits')[:5]
    
    for v in Dayofweekhits.objects.all():
#        if counter >= 3:
#            break
        if v.name == '':
            continue
#        counter += 1
        vDic = {}
        name_type = v.name + ',' + v.type
        vDic[v.weekday] = v.hits
        jsonData[name_type] = vDic  
    
#    counter = 0
    for v in Venuehits.objects.all():
#        if counter >= 3:
#            break
        if v.name == '':
            continue
#        counter += 1
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
    
    return render_to_response('home/statistics.html', context_dict, context)

def about(request):
    context = RequestContext(request)
    
    
    return render_to_response('home/about.html', {}, context)
    
    
    
