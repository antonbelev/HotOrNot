# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#     * Rearrange models' order
#     * Make sure each model has one field with primary_key=True
# Feel free to rename the models, but don't rename db_table values or field names.
#
# Also note: You'll have to insert the output of 'django-admin.py sqlcustom [appname]'
# into your database.

from django.db import models

class Timestamp(models.Model):
    tid = models.IntegerField(primary_key=True, db_column='TID') # Field name made lowercase.
    vid = models.IntegerField(db_column='vID') # Field name made lowercase.
    time = models.TextField(db_column='Time') # Field name made lowercase. This field type is a guess.
    date = models.DateField(db_column='Date') # Field name made lowercase.
    day = models.IntegerField(db_column='Day') # Field name made lowercase.
    class Meta:
        db_table = u'Timestamp'

class Venue(models.Model):
    vid = models.CharField(max_length=72, primary_key=True, db_column='vID') # Field name made lowercase.
    name = models.TextField(db_column='Name') # Field name made lowercase.
    type = models.TextField(db_column='Type') # Field name made lowercase.
    latitude = models.FloatField(db_column='Latitude') # Field name made lowercase.
    longitude = models.FloatField(db_column='Longitude') # Field name made lowercase.
    class Meta:
        db_table = u'Venue'

class Venuecategorycount(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=450, db_column='Name', blank=True) # Field name made lowercase.
    hits = models.CharField(max_length=60, db_column='Hits', blank=True) # Field name made lowercase.
    category = models.CharField(max_length=150, db_column='Category', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'VenueCategoryCount'

class Venuecelebritycount(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=450, db_column='Name', blank=True) # Field name made lowercase.
    hits = models.CharField(max_length=60, db_column='Hits', blank=True) # Field name made lowercase.
    category = models.CharField(max_length=150, db_column='Category', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'VenueCelebrityCount'

class Venuedata(models.Model):
    vid = models.IntegerField(primary_key=True, db_column='vID') # Field name made lowercase.
    name = models.TextField(db_column='Name') # Field name made lowercase.
    hits = models.IntegerField(db_column='Hits') # Field name made lowercase.
    class Meta:
        db_table = u'VenueData'

class Venueday(models.Model):
    id = models.IntegerField(primary_key=True)
    venue_name = models.CharField(max_length=450, blank=True)
    day = models.CharField(max_length=12, blank=True)
    count = models.CharField(max_length=60, blank=True)
    class Meta:
        db_table = u'VenueDay'

class Venuedaycat(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=450, db_column='Name', blank=True) # Field name made lowercase.
    day = models.CharField(max_length=30, db_column='Day', blank=True) # Field name made lowercase.
    hits = models.CharField(max_length=60, db_column='Hits', blank=True) # Field name made lowercase.
    category = models.CharField(max_length=150, db_column='Category', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'VenueDayCat'

class Venueweekday(models.Model):
    id = models.IntegerField(primary_key=True)
    venue_name = models.CharField(max_length=450, blank=True)
    day = models.CharField(max_length=30, blank=True)
    count = models.CharField(max_length=60, blank=True)
    class Meta:
        db_table = u'VenueWeekDay'

class Venueweekdaycat(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=450, db_column='Name', blank=True) # Field name made lowercase.
    weekday = models.CharField(max_length=30, db_column='WeekDay', blank=True) # Field name made lowercase.
    hits = models.CharField(max_length=60, db_column='Hits', blank=True) # Field name made lowercase.
    category = models.CharField(max_length=150, db_column='Category', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'VenueWeekDayCat'

class Venueyear(models.Model):
    id = models.IntegerField(primary_key=True)
    venue_name = models.CharField(max_length=450, blank=True)
    day = models.CharField(max_length=30, blank=True)
    count = models.CharField(max_length=60, blank=True)
    class Meta:
        db_table = u'VenueYear'

class Venueyearcat(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=450, db_column='Name', blank=True) # Field name made lowercase.
    year = models.CharField(max_length=30, db_column='Year', blank=True) # Field name made lowercase.
    hits = models.CharField(max_length=60, db_column='Hits', blank=True) # Field name made lowercase.
    category = models.CharField(max_length=150, db_column='Category', blank=True) # Field name made lowercase.
    class Meta:
        db_table = u'VenueYearCat'

class AuthGroup(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=240, unique=True)
    class Meta:
        db_table = u'auth_group'

class AuthGroupPermissions(models.Model):
    id = models.IntegerField(primary_key=True)
    group_id = models.IntegerField()
    permission_id = models.IntegerField()
    class Meta:
        db_table = u'auth_group_permissions'

class AuthPermission(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=150)
    content_type_id = models.IntegerField()
    codename = models.CharField(max_length=300, unique=True)
    class Meta:
        db_table = u'auth_permission'

class AuthUser(models.Model):
    id = models.IntegerField(primary_key=True)
    password = models.CharField(max_length=384)
    last_login = models.DateTimeField()
    is_superuser = models.IntegerField()
    username = models.CharField(max_length=90, unique=True)
    first_name = models.CharField(max_length=90)
    last_name = models.CharField(max_length=90)
    email = models.CharField(max_length=225)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()
    class Meta:
        db_table = u'auth_user'

class AuthUserGroups(models.Model):
    id = models.IntegerField(primary_key=True)
    user_id = models.IntegerField()
    group_id = models.IntegerField()
    class Meta:
        db_table = u'auth_user_groups'

class AuthUserUserPermissions(models.Model):
    id = models.IntegerField(primary_key=True)
    user_id = models.IntegerField()
    permission_id = models.IntegerField()
    class Meta:
        db_table = u'auth_user_user_permissions'

class DjangoAdminLog(models.Model):
    id = models.IntegerField(primary_key=True)
    action_time = models.DateTimeField()
    user_id = models.IntegerField()
    content_type_id = models.IntegerField(null=True, blank=True)
    object_id = models.TextField(blank=True)
    object_repr = models.CharField(max_length=600)
    action_flag = models.IntegerField()
    change_message = models.TextField()
    class Meta:
        db_table = u'django_admin_log'

class DjangoContentType(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=300)
    app_label = models.CharField(max_length=300, unique=True)
    model = models.CharField(max_length=300, unique=True)
    class Meta:
        db_table = u'django_content_type'

class DjangoSession(models.Model):
    session_key = models.CharField(max_length=120, primary_key=True)
    session_data = models.TextField()
    expire_date = models.DateTimeField()
    class Meta:
        db_table = u'django_session'

class DjangoSite(models.Model):
    id = models.IntegerField(primary_key=True)
    domain = models.CharField(max_length=300)
    name = models.CharField(max_length=150)
    class Meta:
        db_table = u'django_site'

