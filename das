[33mcommit 9a3fbbbae93d730970dcc98eec22f149e870181e[m
Author: Andrii Kalinichenko <kalinichenkoandrew@gmail.com>
Date:   Mon Sep 14 11:12:57 2015 +0300

    Added status for Hire

[33mcommit 0a6809f2ae9d3e5e41b9a6d4bea88b8698ab0db5[m
Author: Oleksandr Ilkun <alexander.ilkun@gmail.com>
Date:   Mon Sep 14 03:33:11 2015 +0300

    Nigthly fun.

[33mcommit f07c2f440bfed3a7a3214bf9f31c7ad2ff2935fa[m
Author: Oleksandr Ilkun <alexander.ilkun@gmail.com>
Date:   Mon Sep 14 00:06:08 2015 +0300

    Some fixes.

[33mcommit 4f89adac239823557d233cb7757f5f1c700155a7[m
Author: Oleksandr Ilkun <alexander.ilkun@gmail.com>
Date:   Sun Sep 13 14:16:00 2015 +0300

    Fixed packaging.

[33mcommit 667ff5693140c38be1eda4376a5c1caa4c4d9e8f[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Sun Sep 13 01:28:14 2015 +0300

    Merge 'master' with 'AccountController'. Move jsp pages from AccountController module to CoreModule.It should not work properly now

[33mcommit 58929974ca6e6e75b04719f0e3a189d9872c4ac8[m
Merge: 619cc90 cd2f03d
Author: YashchukM <19Mihail@gmail.com>
Date:   Sun Sep 13 00:42:07 2015 +0300

    Merge branch 'AccountController'
    
    # Conflicts:
    #	.idea/compiler.xml
    #	.idea/dataSources.ids
    #	.idea/dataSources.local.xml
    #	.idea/dataSources.xml
    #	.idea/encodings.xml
    #	.idea/misc.xml
    #	.idea/modules.xml
    #	.idea/workspace.xml
    #	HireSystem.iml
    #	pom.xml

[33mcommit cd2f03d2d1a0e330fa17fc0ef2396af3f8557351[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Sun Sep 13 00:37:50 2015 +0300

    Fixed dependencies. Changed packages

[33mcommit 619cc90ee0b77aa3046771cf8a86e6278858db30[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Sun Sep 13 00:13:01 2015 +0300

    Fix bug with SolrService. Refactor code

[33mcommit 198260f81006839d69781bfad5a08eaa55879684[m
Merge: ac6eedb 4e8c617
Author: YashchukM <19Mihail@gmail.com>
Date:   Sun Sep 13 00:00:51 2015 +0300

    Merge 'master' with 'alexander-ilkun'. Added CoreModule - main project module. Modified database module. Still not using remote database

[33mcommit 4e8c617e1e44c0170a357f9c92fe7625c8ab1b5e[m
Author: Oleksandr Ilkun <alexander.ilkun@gmail.com>
Date:   Sat Sep 12 15:54:07 2015 +0300

    Added ReviewAndRatingService.

[33mcommit 976f0994a51793f6aa422754d7f224186a283a3d[m
Author: haylen <aleksandr.haylen@gmail.com>
Date:   Thu Sep 10 11:45:21 2015 +0300

    Adding branch AccountController. Main users actions

[33mcommit ac6eedb1deb3c4e111d5b52aeabd51c0b8d2a65c[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Wed Sep 9 11:36:37 2015 +0300

    Connect Solr search with database entities

[33mcommit a7be53371ac50380d22baa1234bf2bec692fb7c7[m
Merge: fc0c09f 8b25d20
Author: YashchukM <19Mihail@gmail.com>
Date:   Tue Sep 8 09:50:13 2015 +0300

    Merge branch 'database'

[33mcommit 8b25d20dcb3189dced13e34f29ff4c51508f811e[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Mon Sep 7 23:26:27 2015 +0300

    Delete Solr module and other unnecessary files from 'database' branch.

[33mcommit eeeca9ef768c2bfaa9aae75d17f107080786b4b0[m
Author: Oleksandr Ilkun <alexander.ilkun@gmail.com>
Date:   Mon Sep 7 18:29:39 2015 +0300

    Added compatibility with Database module.

[33mcommit fc0c09f3df45435a91b81d2d93d7a9eccf7796af[m
Merge: 82416ab 6a59832
Author: YashchukM <19Mihail@gmail.com>
Date:   Mon Sep 7 12:00:30 2015 +0300

    Merge branch 'search_engine'
    
    Merge to use as ready service.

[33mcommit 6a59832a0c6c542950a73c8741b93a257f1b1309[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Mon Sep 7 11:41:36 2015 +0300

    Add  generated address of Solr service core. Add utility class to send request.

[33mcommit a543b7f73ef3e12834fbf9a1161e8bfee95bffd7[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Sun Aug 30 15:37:55 2015 +0300

    Add built-in address of Solr service. Now using SolrHQ. Change names of fields in SolrProduct, now using dynamic fields.

[33mcommit 924843706c5db1f789928cb2b1c2b1720277d343[m
Author: Andrii Kalinichenko <kalinichenkoandrew@gmail.com>
Date:   Wed Aug 5 14:42:18 2015 +0300

    Created managers

[33mcommit 54ce54e9a89f453863ddddf5af6fe3a5f4209b93[m
Author: Andrii Kalinichenko <kalinichenkoandrew@gmail.com>
Date:   Mon Jul 20 16:15:08 2015 +0300

    Created entities

[33mcommit 0559a7ab37483d301058116424665dafb2bda548[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Tue Jul 14 12:10:45 2015 +0300

    Add Spring compatibility, make SolrDaoImpl -> SolrService

[33mcommit f38d9d7e4b33d8ec0c7d3013824407a5f9659573[m
Author: Oleksandr Ilkun <alexander.ilkun@gmail.com>
Date:   Sun Jul 12 19:36:48 2015 +0300

    Trying to implement template of the service using Spring, Spring MVC, Spring Security, JPA, Apache Tiles etc. and Google Material Design Library for the HTML&CSS&JS part.
    
    Functionality:
    Supporting basic registration, login and logout functionality. There are two roles: ROLE_USER, ROLE_ADMIN. Registration uses ROLE_USER. Accounts with the ROLE_ADMIN role can view list of the users and detailed information about the users.
    
    Default admin account:
    Name: admin
    Password: admin

[33mcommit 3774c8d1435d3dda07e29c084358eb28c4e7ecde[m
Author: Your name <you@example.com>
Date:   Sat Jul 11 16:00:19 2015 +0300

[33mcommit 7948f990a3ea8d775ed13c7b0b7a450d9117e655[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Fri Jul 10 15:42:27 2015 +0300

    Add search by category and user

[33mcommit 680e4149d2d7a7eee92646a222059cbd71734e3b[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Fri Jul 10 00:35:46 2015 +0300

    Create product pojo, separate dao interface and implementation

[33mcommit 82416ab230656106944c2743fb1d8ba9e6615fca[m
Author: Myhailo Iaschuk <YashchukM@users.noreply.github.com>
Date:   Tue Jul 7 18:22:26 2015 +0300

    create-readme

[33mcommit 5614d878d3ebd80ce57fe342ba4a01be37a2a2d4[m
Author: YashchukM <19Mihail@gmail.com>
Date:   Tue Jul 7 18:15:44 2015 +0300

    Initial commit
