# Create your views here.
import json
from django.shortcuts import render_to_response
from django.template import RequestContext

def base(request):
    context = RequestContext(request)
    context_dict = {}
    
    #read venues from db here
    venues = ['o2 Academy', 'Firhill Stadium', 'University of Glasgow']
    years = ["2009", "2010", "2011"];
    
    jsonData = { "2009" : {"o2 Academy" : 2534, "University of Glasgow" : 3244, "Firhill Stadium" : 3425},
    "2010" : {"o2 Academy" : 4524, "University of Glasgow" : 4566, "Firhill Stadium" : 7674},
    "2011" : {"o2 Academy" : 1234, "University of Glasgow" : 4534, "Firhill Stadium" : 6343}
    }
    
    context_dict['years'] = years
    context_dict['venues'] = venues  
    context_dict['json'] = json.dumps(jsonData)     
    
    return render_to_response('home/statistics.html', context_dict, context)

def about(request):
    context = RequestContext(request)
    
    
    return render_to_response('home/about.html', {}, context)
    
    
    
